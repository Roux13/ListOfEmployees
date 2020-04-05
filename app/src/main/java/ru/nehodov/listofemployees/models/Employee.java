package ru.nehodov.listofemployees.models;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

    private final String firstName;
    private final String lastName;
    private final Date birthDate;

    private int photo;
    private Profession profession;

    public Employee(String firstName, String lastName, Date birthDate, int photo, Profession profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.photo = photo;
        this.profession = profession;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photoUrl) {
        this.photo = photoUrl;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
