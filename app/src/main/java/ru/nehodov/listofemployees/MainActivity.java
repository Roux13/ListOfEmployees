package ru.nehodov.listofemployees;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;

import static ru.nehodov.listofemployees.EmployeeCardNavigationGraphDirections.ActionGlobalEmployeeCard;
import static ru.nehodov.listofemployees.EmployeeCardNavigationGraphDirections.actionGlobalEmployeeCard;
import static ru.nehodov.listofemployees.EmployeesNavGraphDirections.ActionGlobalEmployeeList;
import static ru.nehodov.listofemployees.EmployeesNavGraphDirections.actionGlobalEmployeeList;

public class MainActivity extends AppCompatActivity implements EmployeeListListener,
        ProfessionListener {

    private EmployeeViewModel viewModel;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host);

        viewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        viewModel.getEmployeeLiveData().observe(this, viewModel::setEmployees);

        NavHostFragment navHostFragment;
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment_container_land);
        } else {
            navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment_container);
        }
        navController = navHostFragment.getNavController();
        navController.navigate(R.id.professionList);
        if (viewModel.isProfessionSelected()) {
            navigateToEmployeeList();
        }
        if (viewModel.isEmployeeSelected()) {
            navigateToEmployeeCard();
        }
    }

    @Override
    public void selectEmployee(Profession profession, Employee employee) {
        viewModel.setSelectedProfession(profession);
        viewModel.setProfessionSelected(true);
        viewModel.setSelectedEmployee(employee);
        viewModel.setEmployeeSelected(true);
        navigateToEmployeeCard();

    }

    private void navigateToEmployeeCard() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            EmployeesNavGraphDirections.ActionGlobalEmployeeCard action
                    = EmployeesNavGraphDirections
                    .actionGlobalEmployeeCard(viewModel.getSelectedEmployee());
            navController.navigate(action);
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ActionGlobalEmployeeCard cardAction =
                    actionGlobalEmployeeCard(viewModel.getSelectedEmployee());
            Navigation.findNavController(this, R.id.employee_card_navigation_graph)
                    .navigate(cardAction);
        }
    }

    @Override
    public void selectProfession(Profession profession) {
        viewModel.setEmployeeSelected(false);
        viewModel.setSelectedProfession(profession);
        viewModel.setProfessionSelected(true);
        navigateToEmployeeList();
    }

    private void navigateToEmployeeList() {
        ActionGlobalEmployeeList action
                = actionGlobalEmployeeList(viewModel.getSelectedProfession());
        navController.navigate(action);
    }

    @Override
    public LiveData<List<Profession>> getProfessions() {
        viewModel.setProfessionSelected(false);
        return viewModel.getAllProfessions();
    }

    @Override
    public List<Employee> getEmployees(Profession profession) {
        return viewModel.getEmployees(profession);
    }
}
