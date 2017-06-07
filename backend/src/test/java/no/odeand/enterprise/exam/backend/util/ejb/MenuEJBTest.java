package no.odeand.enterprise.exam.backend.util.ejb;

import no.odeand.enterprise.exam.backend.entity.Dish;
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

    @Test
    public void testGetCurrentMenu() {

        LocalDate date = LocalDate.now();
        List<Dish> dishes = new ArrayList<>();
        Dish dish = new Dish();
        dish.setDescription("lala");
        dish.setName("lala");
        dishes.add(dish);

        menuEJB.createMenu(date, dishes);


        assertNull(null);

    }

    @Test
    public void testGetAbsentPreviousMenu() {

    }

    @Test
    public void testGetAbsentNextMenu() {

    }

    @Test
    public void testGetPreviousMenu() {

    }

    @Test
    public void testThreeMenus() {

        //Create 3 menus for today, tomorrow and yesterday

        // verify that today has tomorrow as next, and yesterday as previous

        //verify that tomorrow has no next, and today as previous

        //verify that yesterday has no previous, and today as next

    }


}
