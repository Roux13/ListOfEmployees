package ru.nehodov.listofemployees.models;

import java.io.Serializable;

public class Profession implements Serializable {

    private final int id;
    private final String name;

    public Profession(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
