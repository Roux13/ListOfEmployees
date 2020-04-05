package ru.nehodov.listofemployees.stores;

import java.util.ArrayList;
import java.util.List;

import ru.nehodov.listofemployees.models.Agent;
import ru.nehodov.listofemployees.models.AllProfesions;
import ru.nehodov.listofemployees.models.Hacker;
import ru.nehodov.listofemployees.models.Profession;
import ru.nehodov.listofemployees.models.Spy;

public class ProfessionList {

    private static final ProfessionList INSTANCE = new ProfessionList();

    private final List<Profession> professions = new ArrayList<>();

    private ProfessionList() {

        for (int index = 0; index < 30; index += 3) {
            professions.add(new Hacker());
            professions.add(new Agent());
            professions.add(new Spy());
            professions.add(new AllProfesions());
        }
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
