package ru.nehodov.listofemployees.stores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;

public class EmployeeList {

    private static EmployeeList INSTANCE = new EmployeeList();

    private List<Employee> allEmployees = new ArrayList();

    private EmployeeList() {
        allEmployees.add(new Employee("Ivan", "Taranov", new Date(), "url", new Profession(0,"Mock")));
        allEmployees.add(new Employee("John", "Malkovich", new Date(), "url", new Profession(1, "Mock")));
        allEmployees.add(new Employee("Freddy", "Kruger", new Date(), "url", new Profession(2, "Mock")));
    }

    public static EmployeeList getInstance() {
        return INSTANCE;
    }

    public List<Employee> getEmployesByProfession(Profession profession) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (employee.getProfession().getId() == profession.getId()) {
                result.add(employee);
            }
        }
        return result;
    }

    public int getSize() {
        return allEmployees.size();
    }
}
