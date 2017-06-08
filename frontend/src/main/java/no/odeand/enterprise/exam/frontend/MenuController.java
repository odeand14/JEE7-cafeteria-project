package no.odeand.enterprise.exam.frontend;

// Created by Andreas Ødegaard on 08.06.2017.

import no.odeand.enterprise.exam.backend.ejb.MenuEJB;
import no.odeand.enterprise.exam.backend.entity.Dish;
import no.odeand.enterprise.exam.backend.entity.Menu;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class MenuController implements Serializable {

    @EJB
    private MenuEJB menuEJB;

    private LocalDate formDate;

    private List<Dish> menuDishes;

    public void createNewMenu() {
        menuEJB.createMenu(formDate, menuDishes);
    }

    public List<Menu> getAllMenus() {
        return menuEJB.getAllMenus();
    }

    public LocalDate getFormDate() {
        return formDate;
    }

    public void setFormDate(LocalDate formDate) {
        this.formDate = formDate;
    }

}
