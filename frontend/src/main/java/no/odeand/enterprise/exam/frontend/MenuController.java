package no.odeand.enterprise.exam.frontend;

// Created by Andreas Ødegaard on 08.06.2017.

import no.odeand.enterprise.exam.backend.ejb.MenuEJB;
import no.odeand.enterprise.exam.backend.entity.Dish;
import no.odeand.enterprise.exam.backend.entity.Menu;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class MenuController implements Serializable {

    private Map<Long, Boolean> checkMap;
    private List<Dish> menuDishes;

    @EJB
    private MenuEJB menuEJB;

    @Inject
    private DishController dishController;

    private String formDate;

    @PostConstruct
    public void init(){
        menuDishes = new ArrayList<>();
        checkMap = new ConcurrentHashMap<>();
    }

    public void createNewMenu() {
        menuDishes.addAll(dishController.getAllDishes().stream().filter(item -> checkMap.get(item.getId())).collect(Collectors.toList()));
        menuEJB.createMenu(LocalDate.parse(formDate), menuDishes);
        checkMap.clear();
        menuDishes.clear();
    }


    public Menu getMenuOfTheWeek() {
        return menuEJB.getCurrentMenu();
    }

    public List<Menu> getAllMenus() {
        return menuEJB.getAllMenus();
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public Map<Long, Boolean> getCheckMap() {
        return checkMap;
    }

}
