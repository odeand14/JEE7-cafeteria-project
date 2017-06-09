package no.odeand.enterprise.exam.frontend;

import no.odeand.enterprise.exam.frontend.po.DishesPageObject;
import no.odeand.enterprise.exam.frontend.po.HomePageObject;
import no.odeand.enterprise.exam.frontend.po.MenuPageObject;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// Created by Andreas Ødegaard on 07.06.2017.
public class MyCantinaIT extends WebTestBase {

    @Test
    public void testHomePage() {

        HomePageObject hpo = home.toStartingPage();
        assertTrue(hpo.isOnPage());

        //Go on homepage and verify it is indeed the homepage
    }

    @Test
    public void testCreateDish() {

        DishesPageObject dpo = home.toDishes();
        assertTrue(dpo.isOnPage());
        // From homepage, go to the dishes page

        String name = getUniqueTitle();
        String text = getUniqueText();
        // choose a new unique name

        assertFalse(dpo.isTitleTaken(name));
        // verify no dish exists on the page with that given name

        dpo.createDish(name, text);
        // create a dish with that name

        assertTrue(dpo.isDishVisible(name));
        // verify that dish is now displayed

    }

    @Test
    public void testMenu() {

        DishesPageObject dpo = home.toDishes();
        String name1 = getUniqueTitle();
        String text1 = getUniqueText();
        String name2 = getUniqueTitle();
        String text2 = getUniqueText();
        String name3 = getUniqueTitle();
        String text3 = getUniqueText();

        dpo.createDish(name1, text1);
        dpo.createDish(name2, text2);
        dpo.createDish(name3, text3);
        // create 3 new dishes

        home.toStartingPage();
        MenuPageObject mpo = home.toMenus();
        assertTrue(mpo.isOnPage());
        // go on the Menu creation page

        assertEquals(3, mpo.getNumberOfDishes());
        // verify that those 3 dishes are selectable

        List<Integer> positions = new ArrayList<>();
        positions.add(1);
        positions.add(3);
        // select 2 of them

        String date = String.valueOf(LocalDate.now());
        mpo.createMenu(date, positions);
        // create a menu for today

        HomePageObject hpo = home.toStartingPage();
        assertTrue(hpo.isOnPage());
        // on homepage click "show default" to make sure that the created menu for today is displayed

        assertTrue(hpo.isCorrectDate(date));
        // verify that the date of the displayed menu is correct (ie today)

        assertEquals(2, hpo.getNumberOfDishesInDisplayedMenu());
        List<String> dishes = new ArrayList<>();
        dishes.add(name1);
        dishes.add(name3);
        assertTrue(hpo.isCorrectDishesDisplayed(2, dishes));
        // verify that the 2 selected dishes are displayed in the menu, and only those 2.

    }

    @Test
    public void testDifferentDates() {

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate yesterday = LocalDate.now().minusDays(1);

//        home.

        // create 3 menus: one for today one for tomorrow and one for yesterday

        // on homepage click show default to visualize the menu for today, and verify it

        // click next, and verify that the menu of tomorrow is displayed

        // click previous, and verify that the menu of today is displayed

        // click previous and verify that the menu of yesterday is displayed

    }


}
