package ru.nehodov.listofemployees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;

import java.util.List;

import ru.nehodov.listofemployees.fragments.EmployeeCardFragment;
import ru.nehodov.listofemployees.fragments.EmployeeListFragment;
import ru.nehodov.listofemployees.fragments.ProfessionListFragment;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;

public class MainActivity extends AppCompatActivity implements EmployeeListListener,
        ProfessionListener {

    private EmployeeViewModel viewModel;

    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host);

        viewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        viewModel.getEmployeeLiveData().observe(this, viewModel::setEmplooyees);
        fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.host) == null) {
            fm.beginTransaction()
                    .add(R.id.host, getProfessionFrg())
                    .commit();
        }

    }

    public Fragment getProfessionFrg() {
        return ProfessionListFragment.getInstance();
    }

    @Override
    public void selectEmployee(Profession profession, Employee employee) {
        viewModel.setSelectedProfession(profession);
        viewModel.setSelectedEmployee(employee);
        EmployeeCardFragment fragment = EmployeeCardFragment.getInstance(
                viewModel.getSelectedEmployee());
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            fm.beginTransaction()
                    .addToBackStack("Employees_List")
                    .replace(R.id.host, fragment)
                    .commit();
        }
        if (orientation ==  Configuration.ORIENTATION_LANDSCAPE) {
            EmployeeListFragment employeeListFrg = EmployeeListFragment.getInstance(
                    viewModel.getSelectedProfession());
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
    public void selectProfession(Profession profession) {
        viewModel.setSelectedProfession(profession);
        EmployeeListFragment fragment = EmployeeListFragment.getInstance(profession);
        fm.beginTransaction()
                .addToBackStack("Profession_list")
                .replace(R.id.host, fragment)
                .commit();
    }

    @Override
    public LiveData<List<Profession>> getProfessions() {
        return viewModel.getAllProfessions();
    }

    @Override
    public List<Employee> getEmployees(Profession profession) {
        return viewModel.getEmployees(profession);
    }

}
