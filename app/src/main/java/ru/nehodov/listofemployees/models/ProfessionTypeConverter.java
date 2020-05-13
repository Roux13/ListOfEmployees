package ru.nehodov.listofemployees.models;

import androidx.room.TypeConverter;

public class ProfessionTypeConverter {

    @TypeConverter
    public int fromProfession(Profession profession) {
        return profession.getId();
    }

    @TypeConverter
    public Profession toProfession(int professionId) {
        Profession profession;
        switch (professionId) {
            case 0:
                profession = new Hacker();
                break;
            case 1:
                profession = new Agent();
                break;
            case 2:
                profession = new Spy();
                break;
            default:
                return new AllProfesions();
        }
        return profession;
    }

}
