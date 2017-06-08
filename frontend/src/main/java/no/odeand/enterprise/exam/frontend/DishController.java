package no.odeand.enterprise.exam.frontend;

// Created by Andreas Ødegaard on 08.06.2017.

import no.odeand.enterprise.exam.backend.ejb.DishEJB;
import no.odeand.enterprise.exam.backend.entity.Dish;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class DishController implements Serializable {


    @EJB
    private DishEJB dishEJB;

    private String formName;

    private String formText;

    public void createNewDish() {
        dishEJB.createDish(formName, formText);
        clear();
    }

    public List<Dish> getAllDishes() {
        return dishEJB.getAllDishes();
    }

    public void clear() {
        setFormName(null);
        setFormText(null);
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormText() {
        return formText;
    }

    public void setFormText(String formText) {
        this.formText = formText;
    }
}
