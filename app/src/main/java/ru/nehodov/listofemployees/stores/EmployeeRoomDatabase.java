package ru.nehodov.listofemployees.stores;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.nehodov.listofemployees.dao.ProfessionsDao;
import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.dao.EmployeesDao;
import ru.nehodov.listofemployees.models.Agent;
import ru.nehodov.listofemployees.models.AllProfesions;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Hacker;
import ru.nehodov.listofemployees.models.Profession;
import ru.nehodov.listofemployees.models.Spy;

@Database(entities = {Employee.class, Profession.class}, version = 1, exportSchema = false)
public abstract class EmployeeRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    private static volatile EmployeeRoomDatabase instance;

    static final ExecutorService DATABASE_WRITE_EXECUTOR
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String DB_NAME = "employees_db";

    public abstract EmployeesDao employeesDao();

    public abstract ProfessionsDao professionsDao();

    @SuppressWarnings("checkstyle:DeclarationOrder")
    private static RoomDatabase.Callback employeeDBCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            DATABASE_WRITE_EXECUTOR.execute(() -> {
                        ProfessionsDao professionsDao = instance.professionsDao();
                        EmployeesDao employeesDao = instance.employeesDao();
                        employeesDao.deleteAll();
                        professionsDao.deleteAll();
//                            professionsDao.insert(new Hacker());
//                            professionsDao.insert(new Agent());
//                            professionsDao.insert(new Spy());
//                            professionsDao.insert(new AllProfesions());
//
//                        for (int index = 0; index < 100; index += 3) {
//                            employeesDao.insert(new Employee(
//                                    index,
//                                    "Ivan" + index,
//                                    "Taranov",
//                                    new Date(),
//                                    R.drawable.man_big,
//                                    new Hacker())
//                            );
//                            employeesDao.insert(new Employee(
//                                    index + 1,
//                                    "John" + index,
//                                    "Malkovich",
//                                    new Date(),
//                                    R.drawable.user_big,
//                                    new Agent()));
//                            employeesDao.insert(new Employee(
//                                    index + 2,
//                                    "Freddy" + index,
//                                    "Kruger",
//                                    new Date(),
//                                    R.drawable.spy_big,
//                                    new Spy()));
//                        }
                    }
            );
        }
    };

    static EmployeeRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (EmployeeRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeRoomDatabase.class, DB_NAME)
                            .addCallback(employeeDBCallBack)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}
