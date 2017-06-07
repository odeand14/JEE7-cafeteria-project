package no.odeand.enterprise.exam.frontend;

import org.junit.Test;

// Created by Andreas Ødegaard on 07.06.2017.
public class MyCantinaIT {

    @Test
    public void testHomePage() {

        //Go on homepage and verify it is indeed the homepage

    }

    @Test
    public void testCreateDish() {

        // From homepage, go to the dishes page

        // choose a new unique name

        // verify no dish exists on the page with that given name

        // create a dish with that name

        // verify that dish is now displayed

    }

    @Test
    public void testMenu() {

        // create 3 new dishes

        // go on the Menu creation page

        // verify that those 3 dishes are selectable

        // select 2 of them

        // create a menu for today

        // on homepage click "show default" to make sure that the created menu for today is displayed

        // verify that the date of the displayed menu is correct (ie today)

        // verify that the 2 selected dishes are displayed in the menu, and only those 2.

    }

    @Test
    public void testDifferentDates() {

        // create 3 menus: one for today one for tomorrow and one for yesterday

        // on homepage click show default to visualize the menu for today, and verify it

        // click next, and verify that the menu of tomorrow is displayed

        // click previous, and verify that the menu of today is displayed

        // click previous and verify that the menu of yesterday is displayed

    }


}
