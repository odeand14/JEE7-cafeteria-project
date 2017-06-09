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
    private LocalDate currentDate;
    private LocalDate nextDate;
    private LocalDate previousDate;
    private Menu menu;

    @EJB
    private MenuEJB menuEJB;

    @Inject
    private DishController dishController;

    private String formDate;

    @PostConstruct
    public void init(){
        menu = null;
        currentDate = null;
        nextDate = null;
        previousDate = null;
        menuDishes = new ArrayList<>();
        checkMap = new ConcurrentHashMap<>();
    }

    public void createNewMenu() {
        menuDishes.addAll(dishController.getAllDishes().stream().filter(item -> checkMap.get(item.getId())).collect(Collectors.toList()));
        menuEJB.createMenu(LocalDate.parse(formDate), menuDishes);
        menu = menuEJB.getCurrentMenu();
        setMenus();
        checkMap.clear();
        menuDishes.clear();
        setFormDate(null);
    }

    private void setMenus() {
//        menu = menuEJB.getMenu(id);
        currentDate = menu.getDate();
        if (menuEJB.getClosestPastMenu(currentDate) == null) {
            previousDate = null;
        } else {
            previousDate = menuEJB.getClosestPastMenu(currentDate).getDate();
        }
        if (menuEJB.getClosestFutureMenu(currentDate) == null) {
            nextDate = null;
        } else {
            nextDate = menuEJB.getClosestFutureMenu(currentDate).getDate();
        }
    }

    public void getPressedMenu(LocalDate date) {
        menu = menuEJB.getMenuByDate(date);
        setMenus();
//        return "/my_cantina/home.jsf";
    }

    public void getMenuOfTheWeek() {
        menu = menuEJB.getCurrentMenu();
        setMenus();
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

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate getNextDate() {
        return nextDate;
    }

    public void setNextDate(LocalDate nextDate) {
        this.nextDate = nextDate;
    }

    public LocalDate getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(LocalDate previousDate) {
        this.previousDate = previousDate;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
