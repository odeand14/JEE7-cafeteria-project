package no.odeand.enterprise.exam.frontend;

import no.odeand.enterprise.exam.backend.ejb.UserEJB;
import no.odeand.enterprise.exam.backend.entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

// Created by Andreas Ødegaard on 05.06.2017.
@Named
@RequestScoped
public class UserController {

    @EJB
    private UserEJB userEJB;

    public User getUser(String userId){

        return userEJB.getUser(userId);
    }
}
