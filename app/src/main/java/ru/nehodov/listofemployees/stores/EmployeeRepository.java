package ru.nehodov.listofemployees.stores;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.nehodov.listofemployees.dao.ProfessionsDao;
import ru.nehodov.listofemployees.dao.EmployeesDao;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;

public class EmployeeRepository {

    private ProfessionsDao professionsDao;
    private EmployeesDao employeesDao;
    private LiveData<List<Profession>> professions;
    private LiveData<List<Employee>> employees;

    public EmployeeRepository(Application application) {
        EmployeeRoomDatabase db = EmployeeRoomDatabase.getDatabase(application);
        this.professionsDao = db.professionsDao();
        this.employeesDao = db.employeesDao();
        professions = this.professionsDao.getAll();
        employees = this.employeesDao.getAll();
    }

    public LiveData<List<Profession>> getAllProfessions() {
        return professions;
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return employees;
    }
}
