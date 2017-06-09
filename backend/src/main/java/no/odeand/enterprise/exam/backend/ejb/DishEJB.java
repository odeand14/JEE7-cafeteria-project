package no.odeand.enterprise.exam.backend.ejb;

import no.odeand.enterprise.exam.backend.entity.Dish;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

// Created by Andreas Ødegaard on 07.06.2017.

@Stateless
public class DishEJB implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public Long createDish(@NotNull String name, @NotNull String description) {

        Dish dish = new Dish();
        dish.setName(name);
        dish.setDescription(description);
        em.persist(dish);

        return dish.getId();
    }


    public List<Dish> getAllDishes() {

        Query query = em.createNativeQuery(
                "select * from Dish p ", Dish.class);

        return query.getResultList();
    }

    public Dish getDish(Long id) {

        TypedQuery<Dish> query = em.createQuery(
                "select distinct d from Dish d WHERE d.id=?1", Dish.class);
        query.setParameter(1, id);

        return query.getSingleResult();
    }
}
