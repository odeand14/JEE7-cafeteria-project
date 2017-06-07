package no.odeand.enterprise.exam.backend.ejb;

import no.odeand.enterprise.exam.backend.entity.Menu;

import java.time.LocalDate;

// Created by Andreas Ødegaard on 07.06.2017.
public class MenuEJB {

    public LocalDate createMenu() {

        //Create a menu

        return null;
    }

    public Menu getCurrentMenu() {

        //Get the "current" menu. This is defined as: if there is a menu for today, get that.
        // If not, look at the closest menu in the future.
        // If none in the future exists, get the closest in the past.

        return null;
    }

    public Menu getClosestFutureMenu() {

        //Get the closest menu in the future after a given date

        return null;
    }

    public Menu getClosestPastMenu() {

        // Get the closest menu in the past before a given date.

        return null;
    }

}
