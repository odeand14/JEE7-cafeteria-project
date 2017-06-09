package no.odeand.enterprise.exam.backend.util.ejb;

import no.odeand.enterprise.exam.backend.ejb.DishEJB;
import no.odeand.enterprise.exam.backend.ejb.MenuEJB;
import no.odeand.enterprise.exam.backend.entity.Dish;
import no.odeand.enterprise.exam.backend.entity.Menu;
import no.odeand.enterprise.exam.backend.util.util.DeleterEJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;

import javax.ejb.EJB;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Created by Andreas Ødegaard on 07.06.2017.
public abstract class EJBTestBase {

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "no.odeand.enterprise.exam.backend")
                .addClass(DeleterEJB.class)
                .addPackages(true, "org.apache.commons.codec")
                .addAsResource("META-INF/persistence.xml");
    }

    @EJB
    protected DishEJB dishEJB;

    @EJB
    protected MenuEJB menuEJB;


    @EJB
    protected DeleterEJB deleterEJB;

    @Before
    @After
    public void emptyDatabase(){
        dishEJB.getAllDishes().stream().forEach(d ->
                deleterEJB.deleteEntityById(Dish.class, d.getId()));

        deleterEJB.deleteEntities(Dish.class);

        menuEJB.getAllMenus().stream().forEach(m ->
                deleterEJB.deleteEntityById(Menu.class, m.getId()));

        deleterEJB.deleteEntities(Dish.class);
        deleterEJB.deleteEntities(Menu.class);
    }

    protected boolean createDish() {
        return dishEJB.createDish("Food", "good food") > 0;
    }

    //TODO fix this
    protected boolean createMenu(LocalDate date) {
        return menuEJB.createMenu(date, null) > 1;
    }

    protected List<Dish> createDishList() {

        List<Dish> dishes = new ArrayList<>();
        Long dishID1 = dishEJB.createDish("dish one", "dish one content");
        Long dishID2 = dishEJB.createDish("dish two", "dish two content");
        Long dishID3 = dishEJB.createDish("dish three", "dish three content");

        Dish newDish1 = dishEJB.getDish(dishID1);
        Dish newDish2 = dishEJB.getDish(dishID2);
        Dish newDish3 = dishEJB.getDish(dishID3);

        dishes.add(newDish1);
        dishes.add(newDish2);
        dishes.add(newDish3);

        return dishes;
    }

}
