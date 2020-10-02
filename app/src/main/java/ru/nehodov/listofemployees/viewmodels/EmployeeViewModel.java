package ru.nehodov.listofemployees.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.stream.Collectors;

import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;
import ru.nehodov.listofemployees.network.EmployeeRepository;

public class EmployeeViewModel extends AndroidViewModel {

    public static final int ALL_PROFESSIONS_ID = Integer.MAX_VALUE;

    private LiveData<List<Profession>> professions;
    private LiveData<List<Employee>> employeeLiveData;
    private List<Employee> employees;

    private Profession selectedProfession;
    private Employee selectedEmployee;

    private boolean isProfessionSelected;
    private boolean isEmployeeSelected;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        EmployeeRepository repository = new EmployeeRepository(application);
        this.professions = repository.getAllProfessions();
        this.employeeLiveData = repository.getAllEmployees();
        this.isProfessionSelected = false;
        this.isEmployeeSelected = false;
    }

    public LiveData<List<Profession>> getAllProfessions() {
        return this.professions;
    }

    public List<Employee> getEmployees(Profession profession) {

        if (profession.getId() == ALL_PROFESSIONS_ID) {
            return this.employees;
        } else {
            return employees.stream()
                    .filter(employee -> employee.getProfessions().stream()
                                    .anyMatch(prof -> prof.getId() == profession.getId()))
                    .collect(Collectors.toList());
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

    public boolean isProfessionSelected() {
        return isProfessionSelected;
    }

    public void setProfessionSelected(boolean professionSelected) {
        isProfessionSelected = professionSelected;
    }

    public boolean isEmployeeSelected() {
        return isEmployeeSelected;
    }

    public void setEmployeeSelected(boolean employeeSelected) {
        isEmployeeSelected = employeeSelected;
    }
}
