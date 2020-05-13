package ru.nehodov.listofemployees;

import java.util.List;

import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;

public interface EmployeeListListener {

    void selectEmployee(Profession profession, Employee employee);

    List<Employee> getEmployees(Profession profession);

}
