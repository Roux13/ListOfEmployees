package ru.nehodov.listofemployees.dao;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.models.Agent;
import ru.nehodov.listofemployees.models.AllProfesions;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Hacker;
import ru.nehodov.listofemployees.models.Spy;
import ru.nehodov.listofemployees.stores.EmployeeRoomDatabase;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmployeesDaoTest {

    private EmployeeRoomDatabase db;
    private ProfessionsDao professionsDao;
    private EmployeesDao employeesDao;

    @Before
    public void createDB() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getContext(),
                EmployeeRoomDatabase.class)
                .build();
        employeesDao = db.employeesDao();
        professionsDao = db.professionsDao();
        professionsDao.insert(new Hacker());
        professionsDao.insert(new Agent());
        professionsDao.insert(new Spy());
        professionsDao.insert(new AllProfesions());

    }

    @After
    public void closeDB() {
        db.close();
    }

    @Test
    public void whenInsertEmployeeThenGetTheSame() {
        Date date = new Date(System.currentTimeMillis());
        Employee employee = new Employee(
                0,
                "firstName",
                "LastName",
                date,
                123,
                new Hacker());
        employeesDao.insert(employee);
        Employee expected = employee;

        Employee actual = employeesDao.getAll().get(0);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenInsert3EmployeesThenGetListWithSize3AndEmployeeTheSame() {
        Employee employee1 = new Employee(
                0,
                "Ivan",
                "Taranov",
                new Date(),
                R.drawable.man_big,
                new Hacker());
        Employee employee2 = new Employee(
                1,
                "John",
                "Malkovich",
                new Date(),
                R.drawable.user_big,
                new Agent());
        Employee employee3 = new Employee(
                2,
                "Freddy",
                "Kruger",
                new Date(),
                R.drawable.spy_big,
                new Spy());
        List<Employee> expectedList = Arrays.asList(employee1, employee2, employee3);

        employeesDao.insert(employee1);
        employeesDao.insert(employee2);
        employeesDao.insert(employee3);
        List<Employee> actualList = employeesDao.getAll();

        assertThat(actualList, is(expectedList));
        assertThat(actualList.get(0), is(employee1));
        assertThat(actualList.get(1), is(employee2));
        assertThat(actualList.get(2), is(employee3));
    }
}