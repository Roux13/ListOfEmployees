package ru.nehodov.listofemployees;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;
import ru.nehodov.listofemployees.stores.EmployeeRepository;

public class EmployeeViewModel extends AndroidViewModel {

    private static final int ALL_PROFESSIONS_ID = 3;

    private EmployeeRepository repository;

    private LiveData<List<Profession>> professions;
    private LiveData<List<Employee>> employeeLiveData;
    private List<Employee> employees;

    private Profession selectedProfession;
    private Employee selectedEmployee;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        this.repository = new EmployeeRepository(application);
        this.professions = repository.getAllProfessions();
        this.employeeLiveData = repository.getAllEmployees();
    }

    public LiveData<List<Profession>> getAllProfessions() {
        return this.professions;
    }

    public List<Employee> getEmployees(Profession profession) {

        if (profession.getId() == ALL_PROFESSIONS_ID) {
            return this.employees;
        } else {
            List<Employee> result = new ArrayList<>();
            for (Employee employee : employees) {
                for (Profession p : employee.getProfessions()) {
                    if (p.getId() == profession.getId()) {
                        result.add(employee);
                        break;
                    }
                }
            }
            return result;
        }
    }

    public void setSelectedEmployee(Employee employee) {
        this.selectedEmployee = employee;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public Profession getSelectedProfession() {
        return selectedProfession;
    }

    public void setSelectedProfession(Profession selectedProfession) {
        this.selectedProfession = selectedProfession;
    }

    public LiveData<List<Employee>> getEmployeeLiveData() {
        return employeeLiveData;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
