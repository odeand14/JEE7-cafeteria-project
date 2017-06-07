package no.odeand.enterprise.exam.backend.util.ejb;

import no.odeand.enterprise.exam.backend.ejb.DishEJB;
import no.odeand.enterprise.exam.backend.ejb.MenuEJB;
import no.odeand.enterprise.exam.backend.util.util.DeleterEJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import javax.ejb.EJB;
import java.time.LocalDate;

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

    protected boolean createDish() {
        return dishEJB.createDish("Food", "good food") > 0;
    }

    //TODO fix this
    protected boolean createMenu(LocalDate date) {
        return menuEJB.createMenu(date, null) > 1;
    }

}
