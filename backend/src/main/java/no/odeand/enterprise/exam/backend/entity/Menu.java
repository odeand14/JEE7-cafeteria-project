package no.odeand.enterprise.exam.backend.entity;

// Created by Andreas Ødegaard on 07.06.2017.


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private LocalDate date;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE}, mappedBy = "isInMenus")
    private List<Dish> dishesInMenu;

    @PreRemove
    private void removeDishesFromMenu() {
        for (Dish dish : dishesInMenu) {
            dish.getIsInMenus().remove(this);
        }
    }

    public Menu() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDishesInMenu() {
        if (dishesInMenu == null) {
            dishesInMenu = new ArrayList<>();
        }
        return dishesInMenu;
    }

    public void setDishesInMenu(List<Dish> dishesInMenu) {
        this.dishesInMenu = dishesInMenu;
    }

}
