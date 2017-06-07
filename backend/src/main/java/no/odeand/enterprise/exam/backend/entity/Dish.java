package no.odeand.enterprise.exam.backend.entity;

// Created by Andreas Ødegaard on 07.06.2017.


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

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
    private String text;

}
