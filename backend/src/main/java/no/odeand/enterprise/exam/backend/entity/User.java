package no.odeand.enterprise.exam.backend.entity;
// Created by Andreas on 03.06.2017.

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {


    @Id
    @Pattern(regexp = "[A-Za-z0-9]{1,32}")
    private String userId;

    @NotEmpty
    private String hash;

    @NotEmpty @Size(max = 26)
    private String salt;

    @NotBlank
    @Size(min=1 , max = 32)
    private String firstName;

    @Size(min=0 , max = 32)
    private String middleName;

    @NotBlank
    @Size(min=1 , max = 32)
    private String lastName;


    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationTime;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
