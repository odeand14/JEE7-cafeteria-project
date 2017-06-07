package no.odeand.enterprise.exam.backend.ejb;

import no.odeand.enterprise.exam.backend.entity.Dish;
import no.odeand.enterprise.exam.backend.entity.Menu;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

// Created by Andreas Ødegaard on 07.06.2017.

@Stateless
public class MenuEJB {

    private static final String dishFetch = "LEFT JOIN FETCH m.dishes";

    @PersistenceContext
    private EntityManager em;



    public long createMenu(@NotNull LocalDate localDate, @NotEmpty List<Dish> dishes) {

        //Create a menu TODO Make sure no SQL injection!

        Menu menu = new Menu();
        menu.setDate(Date.valueOf(localDate));
        menu.setDishesInMenu(dishes);
        em.persist(menu);

        return menu.getId();
    }

    public Menu getCurrentMenu() {

        //Get the "current" menu. This is defined as: if there is a menu for today, get that.
        // If not, look at the closest menu in the future.
        // If none in the future exists, get the closest in the past.

        return null;
    }

    public Menu getClosestFutureMenu(LocalDate localDate) {

        //Get the closest menu in the future after a given date TODO Make sure no SQL injection!

        TypedQuery<Menu> query = em.createQuery("SELECT DISTINCT m FROM Menu m " + dishFetch + " WHERE m.date < " + localDate + " ORDER BY m.date ASC ", Menu.class );

        return query.getSingleResult();
    }

    public Menu getClosestPastMenu(LocalDate localDate) {

        // Get the closest menu in the past before a given date. TODO Make sure no SQL injection!

        TypedQuery<Menu> query = em.createQuery("SELECT DISTINCT m FROM Menu m " + dishFetch + " WHERE m.date > " + localDate + " ORDER BY m.date DESC ", Menu.class );

        return query.getSingleResult();
    }

}
