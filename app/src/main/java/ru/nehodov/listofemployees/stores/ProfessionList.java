package ru.nehodov.listofemployees.stores;

import java.util.ArrayList;
import java.util.List;

import ru.nehodov.listofemployees.models.Profession;

public class ProfessionList {

    private static final ProfessionList INSTANCE = new ProfessionList();

    private final List<Profession> professions = new ArrayList<>();

    private ProfessionList() {
    }

    public static ProfessionList getInstance() {
        return INSTANCE;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public int getSize() {
        return professions.size();
    }
}
