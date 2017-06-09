package no.odeand.enterprise.exam.backend.ejb;

import no.odeand.enterprise.exam.backend.entity.Dish;
import no.odeand.enterprise.exam.backend.entity.Menu;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

// Created by Andreas Ødegaard on 07.06.2017.

@Stateless
public class MenuEJB implements Serializable {

    public MenuEJB() {}

    @PersistenceContext
    private EntityManager em;


    public Long createMenu(@NotNull LocalDate localDate, @NotEmpty List<Dish> dishes) {

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

    public Menu getCurrentMenu() {
        Menu menu;
        TypedQuery<Menu> query = em.createQuery("SELECT distinct m FROM Menu m WHERE m.date = '" + LocalDate.now() + "'", Menu.class );
        if ((menu = query.getResultList().stream().findFirst().orElse(null)) != null) {
            return menu;
        } else if ((menu = getClosestFutureMenu(LocalDate.now())) != null) {
            return menu;
        } else {
            return getClosestPastMenu(LocalDate.now());
        }
    }

    public Menu getClosestFutureMenu(LocalDate localDate) {
        if (localDate == null) return null;

        TypedQuery<Menu> query = em.createQuery("SELECT distinct m FROM Menu m WHERE m.date > :date ORDER BY m.date ASC ", Menu.class);

        return query.setParameter("date", localDate).getResultList().stream().findFirst().orElse(null);
    }

    public Menu getClosestPastMenu(LocalDate localDate) {
        if (localDate == null) return null;

        TypedQuery<Menu> query = em.createQuery("SELECT m FROM Menu m WHERE m.date < :date ORDER BY m.date DESC ", Menu.class );

        return query.setParameter("date", localDate).getResultList().stream().findFirst().orElse(null);
    }

    public Menu getMenuByDate(LocalDate date) {
        if (date == null) return null;

        TypedQuery<Menu> query = em.createQuery("SELECT m FROM Menu m WHERE m.date = :date", Menu.class );

        return query.setParameter("date", date).getSingleResult();
    }

    public Menu getMenu(Long id) {

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
