package ru.nehodov.listofemployees.models;

import java.util.Date;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final Date birthDate;

    private String photoUrl;
    private Profession profession;

    public Employee(String firstName, String lastName, Date birthDate, String photoUrl, Profession profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.photoUrl = photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
