package ru.nehodov.listofemployees.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProfessionTypeConverter {

    @TypeConverter
    public String fromProfessions(List<Profession> profession) {
        return new Gson().toJson(profession);
    }

    @TypeConverter
    public List<Profession> toProfessions(String json) {
        Type listType = new TypeToken<List<Profession>>() { }.getType();
        return new Gson().fromJson(json, listType);
    }

}
