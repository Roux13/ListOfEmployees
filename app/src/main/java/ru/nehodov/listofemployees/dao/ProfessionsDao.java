package ru.nehodov.listofemployees.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.nehodov.listofemployees.models.Profession;

@Dao
public interface ProfessionsDao {

    @Insert
    void insert(Profession profession);

    @Query("SELECT * FROM professions")
    LiveData<List<Profession>> getAll();

    @Query("DELETE FROM professions")
    void deleteAll();
}
