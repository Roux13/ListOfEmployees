package ru.nehodov.listofemployees.models;

import androidx.room.TypeConverter;

import java.util.Date;

public class BirthDateTypeConverter {

    @TypeConverter
    public long fromBirtrhdate(Date birthdate) {
        return birthdate.getTime();
    }

    @TypeConverter
    public Date toBirtdate(long time) {
        return new Date(time);
    }
}
