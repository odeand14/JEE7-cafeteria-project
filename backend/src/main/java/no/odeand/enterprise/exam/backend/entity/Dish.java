package no.odeand.enterprise.exam.backend.entity;

// Created by Andreas Ødegaard on 07.06.2017.


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dish implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 32)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 1024)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Menu> isInMenus;



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
        if (isInMenus == null) {
            return new ArrayList<>();
        }
        return isInMenus;
    }

    public void setIsInMenus(List<Menu> isInMenus) {
        this.isInMenus = isInMenus;
    }
}
