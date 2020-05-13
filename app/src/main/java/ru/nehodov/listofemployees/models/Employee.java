package ru.nehodov.listofemployees.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = "employees",
foreignKeys = @ForeignKey(entity = Profession.class, parentColumns = "id", childColumns = "profession_id"))
public class Employee implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "last_name")
    private String lastName;
    @ColumnInfo(name = "birth_date")
    @TypeConverters({BirthDateTypeConverter.class})
    private Date birthDate;
    @ColumnInfo(name = "photo")
    private int photo;
    @ColumnInfo(name = "profession_id")
    @TypeConverters({ProfessionTypeConverter.class})
    private Profession profession;

    public Employee(int id, String firstName, String lastName, Date birthDate, int photo, Profession profession) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.photo = photo;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photoUrl) {
        this.photo = photoUrl;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                photo == employee.photo &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(profession, employee.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, photo, profession);
    }
}
