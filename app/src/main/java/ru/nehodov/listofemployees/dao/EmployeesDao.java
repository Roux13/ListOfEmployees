package ru.nehodov.listofemployees.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.nehodov.listofemployees.models.Employee;

@Dao
public interface EmployeesDao {

    @Insert
    void insert(Employee employee);

    @Query("SELECT * FROM employees")
    LiveData<List<Employee>> getAll();

    @Query("DELETE FROM employees")
    void deleteAll();

}
