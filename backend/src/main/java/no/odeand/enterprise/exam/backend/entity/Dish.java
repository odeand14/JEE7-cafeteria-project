package no.odeand.enterprise.exam.backend.entity;

// Created by Andreas Ødegaard on 07.06.2017.


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 1, max = 1024)
    private String name;

    @NotBlank
    @Size(min = 1, max = 1024)
    private String description;

    //TODO reverse owning?

    @ManyToMany
    @JoinTable(name = "jnd_dish_menu",
            joinColumns = @JoinColumn(name = "dish_fk"),
            inverseJoinColumns = @JoinColumn(name = "menu_fk"))
    private List<Menu> isInMenus;

    public Dish() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Menu> getIsInMenus() {
        return isInMenus;
    }

    public void setIsInMenus(List<Menu> isInMenus) {
        this.isInMenus = isInMenus;
    }
}
