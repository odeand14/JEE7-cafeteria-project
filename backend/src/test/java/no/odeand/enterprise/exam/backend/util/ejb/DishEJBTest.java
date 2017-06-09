package no.odeand.enterprise.exam.backend.util.ejb;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

// Created by Andreas Ødegaard on 07.06.2017.
@RunWith(Arquillian.class)
public class DishEJBTest extends EJBTestBase{


    @Test
    public void testCreateDish() {

        String dishName = "dish";
        String dishDescription = "description";

        assertEquals(0, dishEJB.getAllDishes().size());

        long id = dishEJB.createDish(dishName, dishDescription);

        assertEquals(1, dishEJB.getAllDishes().size());

        assertEquals(dishName, dishEJB.getDish(id).getName());

    }

    @Test
    public void testCreateTwoDishes() {

        String dishNameOne = "dish one";
        String dishNameTwo = "dish two";
        String dishDescriptionOne = "description one";
        String dishDescriptionTwo = "description two";

        assertEquals(0, dishEJB.getAllDishes().size());

        long idOne = dishEJB.createDish(dishNameOne, dishDescriptionOne);

        assertEquals(dishNameOne, dishEJB.getDish(idOne).getName());

        long idTwo = dishEJB.createDish(dishNameTwo, dishDescriptionTwo);

        assertEquals(dishDescriptionTwo, dishEJB.getDish(idTwo).getDescription());

        assertEquals(2, dishEJB.getAllDishes().size());

    }




}
