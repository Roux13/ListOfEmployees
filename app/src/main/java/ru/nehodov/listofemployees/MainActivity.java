package ru.nehodov.listofemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;

import ru.nehodov.listofemployees.fragments.EmployeeCardFragment;
import ru.nehodov.listofemployees.fragments.EmployeeListFragment;
import ru.nehodov.listofemployees.fragments.ProfessionListFragment;
import ru.nehodov.listofemployees.models.Employee;

public class MainActivity extends AppCompatActivity implements EmployeeListFragment.EmployeeSelect, ProfessionListFragment.ProfessionSelect {

    private static final String EMPLOYEE_KEY = "employee_key";
    private static final String ID_KEY = "profession_id_key";

    private FragmentManager fm;
    private int professionId = -1;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host);

        fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.host) == null) {
            fm.beginTransaction()
                    .add(R.id.host, getFrg())
                    .commit();
        }

        if (savedInstanceState != null) {
            professionId = savedInstanceState.getInt(ID_KEY);
            employee = (Employee) savedInstanceState.getSerializable(EMPLOYEE_KEY);
            if (professionId != -1)
            selectedEmployee(professionId, employee);
        }
    }

    public Fragment getFrg() {
        return ProfessionListFragment.getInstance();
    }

    @Override
    public void selectedEmployee(int professionId, Employee employee) {
        this.professionId = professionId;
        this.employee = employee;
        EmployeeCardFragment fragment = EmployeeCardFragment.getInstance(employee);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            fm.beginTransaction()
                    .addToBackStack("Employees_List")
                    .replace(R.id.host, fragment)
                    .commit();
        }
        if (orientation ==  Configuration.ORIENTATION_LANDSCAPE) {
            EmployeeListFragment employeeListFrg = EmployeeListFragment.getInstance(professionId);
            fm.beginTransaction()
                    .replace(R.id.host, employeeListFrg)
                    .commit();
            if (findViewById(R.id.detail) == null) {
                fm.beginTransaction()
                        .add(R.id.detail, fragment)
                        .commit();
            } else {
                fm.beginTransaction()
                        .replace(R.id.detail, fragment)
                        .commit();
            }
        }
    }

    @Override
    public void selectedProfession(int professionId) {
        EmployeeListFragment fragment = EmployeeListFragment.getInstance(professionId);
        fm.beginTransaction()
                .addToBackStack("Profession_list")
                .replace(R.id.host, fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ID_KEY, professionId);
        outState.putSerializable(EMPLOYEE_KEY, employee);
    }
}
