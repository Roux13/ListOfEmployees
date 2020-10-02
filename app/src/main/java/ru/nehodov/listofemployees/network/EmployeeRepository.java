package ru.nehodov.listofemployees.network;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nehodov.listofemployees.BuildConfig;
import ru.nehodov.listofemployees.viewmodels.EmployeeViewModel;
import ru.nehodov.listofemployees.dao.ProfessionsDao;
import ru.nehodov.listofemployees.dao.EmployeesDao;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;
import ru.nehodov.listofemployees.stores.EmployeeRoomDatabase;

public class EmployeeRepository {

    private static final String TAG = "EmployeeRepository";

    private RestApiInterface restApi;

    private ProfessionsDao professionsDao;
    private EmployeesDao employeesDao;
    private LiveData<List<Profession>> professions;
    private LiveData<List<Employee>> employees;

    private List<Employee> employeesFromJson;

    public EmployeeRepository(Application application) {
        EmployeeRoomDatabase db = EmployeeRoomDatabase.getDatabase(application);
        this.professionsDao = db.professionsDao();
        this.employeesDao = db.employeesDao();
        this.professions = professionsDao.getAll();
        this.employees = employeesDao.getAll();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(
                BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.BASIC);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Roux13/task-json/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.restApi = retrofit.create(RestApiInterface.class);
        uploadAllEmployeesFomWeb();
    }

    public LiveData<List<Profession>> getAllProfessions() {
        return this.professions;
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return this.employees;
    }

    private void uploadAllEmployeesFomWeb() {
        Call<ru.nehodov.listofemployees.models.Response> call = restApi.getAllEmployees();
        call.enqueue(new Callback<ru.nehodov.listofemployees.models.Response>() {
            @Override
            public void onResponse(Call<ru.nehodov.listofemployees.models.Response> call,
                                   Response<ru.nehodov.listofemployees.models.Response> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Response is successful");
                    employeesFromJson = response.body().getEmployees();
                    setDataToDatabase();
                    EmployeeRepository.this.professions = professionsDao.getAll();
                    EmployeeRepository.this.employees = employeesDao.getAll();
                } else {
                    Log.d(TAG, "Is not successful");
                    Log.d(
                            TAG,
                            String.valueOf(response.code()) + response.body());
                }
            }

            @Override
            public void onFailure(Call<ru.nehodov.listofemployees.models.Response> call,
                                  Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void setDataToDatabase() {
        Map<Integer, Profession> professionMap = new HashMap<>();
        for (int i = 0; i < employeesFromJson.size(); i++) {
            employeesFromJson.get(i).setId(i);
            for (Profession p : employeesFromJson.get(i).getProfessions()) {
                professionMap.put(p.getId(), p);
            }
        }
        List<Profession> professions = new ArrayList<>(professionMap.values());
        EmployeeRoomDatabase.DATABASE_WRITE_EXECUTOR.execute(() -> {
            professionsDao.deleteAll();
            for (Profession p : professions) {
                professionsDao.insert(p);
            }
            Profession allProfessions = new Profession("AllProfessions");
            allProfessions.setId(EmployeeViewModel.ALL_PROFESSIONS_ID);
            professionsDao.insert(allProfessions);
        });
        EmployeeRoomDatabase.DATABASE_WRITE_EXECUTOR.execute(() -> {
            employeesDao.deleteAll();
            for (Employee e : employeesFromJson) {
                employeesDao.insert(e);
            }
        });
    }

}
