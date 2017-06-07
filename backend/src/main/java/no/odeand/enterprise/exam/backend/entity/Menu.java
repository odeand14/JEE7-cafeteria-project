package no.odeand.enterprise.exam.backend.entity;

// Created by Andreas Ødegaard on 07.06.2017.


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Menu {

    @Id
    private LocalDate date;

    @NotNull
    @ManyToMany
    private List<Dish> dishes;

}
