//package ru.nehodov.listofemployees.stores;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import ru.nehodov.listofemployees.R;
//import ru.nehodov.listofemployees.models.Agent;
//import ru.nehodov.listofemployees.models.Employee;
//import ru.nehodov.listofemployees.models.Hacker;
//import ru.nehodov.listofemployees.models.Spy;
//
//public class EmployeeList {
//
//    private static EmployeeList INSTANCE = new EmployeeList();
//
//    private List<Employee> allEmployees = new ArrayList<>();
//
//    private EmployeeList() {
//        for (int index = 0; index < 100; index += 3) {
//            allEmployees.add(new Employee("Ivan" + index, "Taranov", new Date(), R.drawable.man_big, new Hacker()));
//            allEmployees.add(new Employee("John" + index, "Malkovich", new Date(), R.drawable.user_big, new Agent()));
//            allEmployees.add(new Employee("Freddy" + index, "Kruger", new Date(), R.drawable.spy_big, new Spy()));
//        }
//    }
//
//    public static EmployeeList getInstance() {
//        return INSTANCE;
//    }
//
//    public List<Employee> getAllEmployees() {
//        return allEmployees;
//    }
//
//    public List<Employee> getEmployeesByProfession(int id) {
//        List<Employee> result = new ArrayList<>();
//        if (id == 3) {
//            result = getAllEmployees();
//        } else {
//            for (Employee employee : allEmployees) {
//                if (employee.getProfession().getId() == id) {
//                    result.add(employee);
//                }
//            }
//        }
//        return result;
//    }
//
//    public int getSize() {
//        return allEmployees.size();
//    }
//}
