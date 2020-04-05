package ru.nehodov.listofemployees.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.models.Employee;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeCardFragment extends Fragment {

    public static final String EMPLOYEE_CARD = "employee_card";

    private Employee employee;

    public EmployeeCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employee_card, container, false);
        TextView firstNameTextView = view.findViewById(R.id.first_name_blanc);
        TextView lastNameTextView = view.findViewById(R.id.last_name_blanc);
        TextView birthDateTextView = view.findViewById(R.id.birth_date_blanc);
        TextView professionTextView = view.findViewById(R.id.profession_blanc);
        ImageView imageView = view.findViewById(R.id.employee_big_image);

        if (savedInstanceState == null) {
            employee = (Employee) getArguments().getSerializable(EMPLOYEE_CARD);
        } else {
            employee = (Employee) savedInstanceState.getSerializable(EMPLOYEE_CARD);
        }

        firstNameTextView.setText(employee.getFirstName());
        lastNameTextView.setText(employee.getLastName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        birthDateTextView.setText(dateFormat.format(employee.getBirthDate()));
        professionTextView.setText(employee.getProfession().getName());
        imageView.setImageResource(employee.getPhoto());
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EMPLOYEE_CARD, employee);
    }
}