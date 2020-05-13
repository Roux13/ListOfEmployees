package ru.nehodov.listofemployees;


import androidx.lifecycle.LiveData;

import java.util.List;

import ru.nehodov.listofemployees.models.Profession;

public interface ProfessionListener {
    void selectProfession(Profession profession);

    LiveData<List<Profession>> getProfessions();
}
