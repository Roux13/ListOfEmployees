package ru.nehodov.listofemployees.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity(tableName = "employees")
//foreignKeys = @ForeignKey(
//        entity = Profession.class, parentColumns = "id",
//        childColumns = "professions"))
public class Employee implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("f_name")
    @ColumnInfo(name = "first_name")
    private String firstName;

    @SerializedName("l_name")
    @ColumnInfo(name = "last_name")
    private String lastName;

    @SerializedName("birthday")
    @ColumnInfo(name = "birth_date")
//    @TypeConverters({BirthDateTypeConverter.class})
    private String birthDate;

    @SerializedName("avatr_url")
    @ColumnInfo(name = "photo")
    private String photo;

    @SerializedName("specialty")
    @ColumnInfo(name = "professions")
    @TypeConverters({ProfessionTypeConverter.class})
    private List<Profession> professions;

    public Employee(
            int id, String firstName, String lastName,
            String birthDate, String photo, List<Profession> professions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.photo = photo;
        this.professions = professions;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photoUrl) {
        this.photo = photoUrl;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(List<Profession> professions) {
        this.professions = professions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(birthDate, employee.birthDate)
                && Objects.equals(photo, employee.photo)
                && Objects.equals(professions, employee.professions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, photo, professions);
    }
}
