package no.odeand.enterprise.exam.backend.util.ejb;

import no.odeand.enterprise.exam.backend.entity.Dish;
import no.odeand.enterprise.exam.backend.entity.Menu;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// Created by Andreas Ødegaard on 07.06.2017.
@RunWith(Arquillian.class)
public class MenuEJBTest extends EJBTestBase{

    //Todo write tests

    @Test(expected = EJBException.class)
    public void testCreateMenuWithNoDish() {

        LocalDate date = LocalDate.now();
        List<Dish> noDishes = new ArrayList<>();

        menuEJB.createMenu(date, noDishes);

    }

//TODO Move down

    @Test
    public void testCreateMenuWithDish() {
        LocalDate date = LocalDate.now();

        assertEquals(0, menuEJB.getAllMenus().size());

        List<Dish> dishes = createDishList();

        Long menuID = menuEJB.createMenu(date, dishes);

        assertEquals(1, menuEJB.getAllMenus().size());

        Menu menu = menuEJB.getMenu(menuID);

        assertTrue(menu.getDishesInMenu().size() == 3);

    }

    @Test
    public void testGetCurrentMenu() {
        assertEquals(0, menuEJB.getAllMenus().size());

        LocalDate dateNow = LocalDate.now();
        LocalDate dateBefore = LocalDate.now().minusDays(3);
        LocalDate dateAfter = LocalDate.now().plusDays(3);

        List<Dish> dishes = createDishList();

        Long idNow = menuEJB.createMenu(dateNow, dishes);
        Long idBefore = menuEJB.createMenu(dateBefore, dishes);
        Long idAfter = menuEJB.createMenu(dateAfter, dishes);

        assertTrue(menuEJB.getCurrentMenu().getDishesInMenu().size() == 3);

        assertEquals(menuEJB.getCurrentMenu().getId(), menuEJB.getMenu(idNow).getId());

        deleterEJB.deleteEntityById(Menu.class, idNow);

        assertEquals(menuEJB.getCurrentMenu().getId(), idAfter);

        deleterEJB.deleteEntityById(Menu.class, idAfter);

        assertEquals(menuEJB.getCurrentMenu().getId(), idBefore);

        deleterEJB.deleteEntityById(Menu.class, idBefore);

        assertNull(menuEJB.getCurrentMenu());

    }


    public void testGetAbsentPreviousMenu() {
        assertEquals(0, menuEJB.getAllMenus().size());

        LocalDate dateNow = LocalDate.now();
        LocalDate dateAfter = LocalDate.now().plusDays(3);

        List<Dish> dishes = createDishList();

        menuEJB.createMenu(dateNow, dishes);
        menuEJB.createMenu(dateAfter, dishes);

        assertEquals(2, menuEJB.getAllMenus().size());

        assertNull(menuEJB.getClosestPastMenu(dateNow));

    }

    @Test
    public void testGetAbsentNextMenu() {
        assertEquals(0, menuEJB.getAllMenus().size());

        LocalDate dateNow = LocalDate.now();
        LocalDate dateBefore = LocalDate.now().minusDays(3);

        List<Dish> dishes = createDishList();

        menuEJB.createMenu(dateNow, dishes);
        menuEJB.createMenu(dateBefore, dishes);

        assertEquals(2, menuEJB.getAllMenus().size());

        assertNull(menuEJB.getClosestFutureMenu(dateNow));
    }

    @Test
    public void testGetPreviousMenu() {
        LocalDate dateNow = LocalDate.now();
        LocalDate dateAfter = LocalDate.now().minusDays(3);
        LocalDate dateAfterAgain = LocalDate.now().minusDays(10);

        List<Dish> dishes = createDishList();

        Long idNow = menuEJB.createMenu(dateNow, dishes);
        Long idAfter = menuEJB.createMenu(dateAfter, dishes);
        Long idAfterAgain = menuEJB.createMenu(dateAfterAgain, dishes);

        assertEquals(3, menuEJB.getAllMenus().size());

        assertEquals(menuEJB.getClosestPastMenu(dateNow).getId(), menuEJB.getMenu(idAfter).getId());

        dateNow = LocalDate.now().minusDays(7);

        assertEquals(menuEJB.getClosestPastMenu(dateNow).getId(), menuEJB.getMenu(idAfterAgain).getId());
    }

    @Test
    public void testThreeMenus() {
        //Create 3 menus for today, tomorrow and yesterday
        LocalDate dateNow = LocalDate.now();
        LocalDate dateYesterday = LocalDate.now().minusDays(1);
        LocalDate dateTomorrow = LocalDate.now().plusDays(1);

        List<Dish> dishes = createDishList();

        Long idToday = menuEJB.createMenu(dateNow, dishes);
        Long idYesterday = menuEJB.createMenu(dateYesterday, dishes);
        Long idTomorrow = menuEJB.createMenu(dateTomorrow, dishes);

        // verify that today has tomorrow as next, and yesterday as previous

        assertEquals(menuEJB.getClosestFutureMenu(dateNow).getId(), idTomorrow);
        assertEquals(menuEJB.getClosestPastMenu(dateNow).getId(), idYesterday);

        //verify that tomorrow has no next, and today as previous

        assertNull(menuEJB.getClosestFutureMenu(dateTomorrow));
        assertEquals(menuEJB.getClosestPastMenu(dateTomorrow).getId(), idToday);

        //verify that yesterday has no previous, and today as next

        assertNull(menuEJB.getClosestPastMenu(dateYesterday));
        assertEquals(menuEJB.getClosestFutureMenu(dateYesterday).getId(), idToday);

    }


}
