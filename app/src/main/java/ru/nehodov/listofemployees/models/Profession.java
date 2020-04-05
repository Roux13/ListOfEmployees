package ru.nehodov.listofemployees.models;

public class Profession {

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
