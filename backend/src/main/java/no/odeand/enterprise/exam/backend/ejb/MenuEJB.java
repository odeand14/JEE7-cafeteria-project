package no.odeand.enterprise.exam.backend.ejb;

import no.odeand.enterprise.exam.backend.entity.Dish;
import no.odeand.enterprise.exam.backend.entity.Menu;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

// Created by Andreas Ødegaard on 07.06.2017.

@Stateless
public class MenuEJB implements Serializable {

    public MenuEJB() {}

    private static final String dishFetch = "SELECT DISTINCT m FROM Menu m INNER JOIN m.dishesInMenu dishes";


    @PersistenceContext
    private EntityManager em;

//    public void addDish(long menuId, Long dishId){
//        Dish dish = em.find(Dish.class, dishId);
//        Menu menu = em.find(Menu.class, menuId);
//
//        if(menu.getDishesInMenu().stream().anyMatch(d -> d.getId().equals(dishId))){
//            return;
//        }
//
//        menu.getDishesInMenu().add(dish);
//        dish.getIsInMenus().add(menu);
//    }

    public Long createMenu(@NotNull LocalDate localDate, @NotEmpty List<Dish> dishes) {

        //Create a menu TODO Make sure no SQL injection!

        Menu menu = new Menu();
        menu.setDate(localDate);
        menu.setDishesInMenu(dishes);
        em.persist(menu);
        for (Dish d: dishes) {
            Dish dish = em.find(Dish.class, d.getId());
            dish.getIsInMenus().add(menu);
        }

        return menu.getId();
    }

    public Menu getCurrentMenu() throws NoResultException {
        Menu menu;
        TypedQuery<Menu> query = em.createQuery("SELECT distinct m FROM Menu m WHERE m.date = '" + LocalDate.now() + "'", Menu.class );
        if (query.getSingleResult() != null) {
            return query.getSingleResult();
        } else if ((menu = getClosestFutureMenu(LocalDate.now())) != null) {
            return menu;
        } else if ((menu = getClosestPastMenu(LocalDate.now())) != null) {
            return menu;
        }
        //Get the "current" menu. This is defined as: if there is a menu for today, get that.
        // If not, look at the closest menu in the future.
        // If none in the future exists, get the closest in the past.
        return null;
    }

    public Menu getClosestFutureMenu(LocalDate localDate) {

        //Get the closest menu in the future after a given date TODO Make sure no SQL injection!
        TypedQuery<Menu> query = em.createQuery("SELECT distinct m FROM Menu m WHERE m.date > '" + localDate + "' ORDER BY m.date ASC ", Menu.class);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public Menu getClosestPastMenu(LocalDate localDate) throws NoResultException {

        // Get the closest menu in the past before a given date. TODO Make sure no SQL injection!
        TypedQuery<Menu> query = em.createQuery("SELECT m FROM Menu m WHERE m.date < '" + localDate + "' ORDER BY m.date DESC ", Menu.class );

        return query.getResultList().stream().findFirst().orElse(null);
    }

    public Menu getMenu(Long id) {

        //TODO Make sure no SQL injection!

        TypedQuery<Menu> query = em.createQuery(
                "select distinct m from Menu m WHERE m.id=?1", Menu.class);
        query.setParameter(1, id);

        return query.getSingleResult();
    }

    public List<Menu> getAllMenus() {

        Query query = em.createNativeQuery(
                "select * from Menu m ", Menu.class);
        return query.getResultList();
    }

}
