package no.odeand.enterprise.exam.backend.entity;

// Created by Andreas Ødegaard on 07.06.2017.


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(unique = true)
    private Date date;

    //TODO reverse owning?

    @NotNull
    @ManyToMany(mappedBy = "isInMenus")
    private List<Dish> dishesInMenu;

    public Menu() {
        dishesInMenu = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Dish> getDishesInMenu() {
        return dishesInMenu;
    }

    public void setDishesInMenu(List<Dish> dishesInMenu) {
        this.dishesInMenu = dishesInMenu;
    }

}
