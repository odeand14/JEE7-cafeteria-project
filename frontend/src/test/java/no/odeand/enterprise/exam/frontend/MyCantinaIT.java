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

        String date = String.valueOf(LocalDate.now().plusDays(123));
        mpo.createMenu(date, positions);
        // create a menu for today

        HomePageObject hpo = home.toStartingPage();
        assertTrue(hpo.isOnPage());
        // on homepage click "show default" to make sure that the created menu for today is displayed

        assertTrue(hpo.isCorrectDate(date));
        // verify that the date of the displayed menu is correct (ie today)

        assertEquals(2, hpo.getNumberOfDishesInDisplayedMenu());
        // verify that the 2 selected dishes are displayed in the menu, and only those 2.

    }

    @Test
    public void testDifferentDates() {

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
        assertTrue(home.isOnPage());

        MenuPageObject mpo = home.toMenus();
        assertTrue(mpo.isOnPage());
        // go on the Menu creation page

        List<Integer> positions1 = new ArrayList<>();
        positions1.add(1);
        positions1.add(3);

        List<Integer> positions2 = new ArrayList<>();
        positions2.add(2);

        List<Integer> positions3 = new ArrayList<>();
        positions3.add(1);
        positions3.add(2);
        positions3.add(3);

        String today = LocalDate.now().toString();
        String tomorrow = LocalDate.now().plusDays(1).toString();
        String yesterday = LocalDate.now().minusDays(1).toString();

        mpo.createMenu(today, positions1);
        mpo.createMenu(tomorrow, positions2);
        mpo.createMenu(yesterday, positions3);
        // create 3 menus: one for today one for tomorrow and one for yesterday

        HomePageObject hpo = home.toStartingPage();
        assertTrue(home.isOnPage());
        assertTrue(hpo.clickOnLink("currentDishLink"));
        assertTrue(hpo.isCorrectDate(today));
        assertEquals(2, hpo.getNumberOfDishesInDisplayedMenu());
        // on homepage click show default to visualize the menu for today, and verify it


        assertTrue(hpo.clickOnLink("nextDishLink"));
        assertTrue(hpo.isCorrectDate(tomorrow));
        assertEquals(1, hpo.getNumberOfDishesInDisplayedMenu());
        // click next, and verify that the menu of tomorrow is displayed


        assertTrue(hpo.clickOnLink("previousDishLink"));
        assertTrue(hpo.isCorrectDate(today));
        assertEquals(2, hpo.getNumberOfDishesInDisplayedMenu());
        // click previous, and verify that the menu of today is displayed


        assertTrue(hpo.clickOnLink("previousDishLink"));
        assertTrue(hpo.isCorrectDate(yesterday));
        assertEquals(3, hpo.getNumberOfDishesInDisplayedMenu());
        // click previous and verify that the menu of yesterday is displayed

    }


}
