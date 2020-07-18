package ru.nehodov.listofemployees.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;

public class EmployeeCardFragment extends Fragment {

    private static final String EMPLOYEE_CARD = "employee_card";

    private Employee employee;

    public EmployeeCardFragment() {
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

        if (savedInstanceState == null && getArguments() != null) {
            try {
                EmployeeCardFragmentArgs fragmentArgs
                        = EmployeeCardFragmentArgs.fromBundle(getArguments());
                employee = fragmentArgs.getEmployee();
            } catch (IllegalArgumentException e) {
                view = inflater.inflate(R.layout.empty_layout, container, false);
                return view;
            }
        } else if (savedInstanceState != null) {
            employee = (Employee) savedInstanceState.getSerializable(EMPLOYEE_CARD);
        } else {
            return view;
        }

        if (employee != null) {
            firstNameTextView.setText(employee.getFirstName());
            lastNameTextView.setText(employee.getLastName());
            birthDateTextView.setText(employee.getBirthDate());
            StringBuffer professionName = new StringBuffer();
            for (Profession profession : employee.getProfessions()) {
                professionName.append(profession.getName()).append(System.lineSeparator());
            }
            professionTextView.setText(professionName);
            if (employee.getPhoto() != null && !employee.getPhoto().equals("")) {
                Picasso.get().load(employee.getPhoto())
                        .placeholder(R.drawable.user_big)
                        .error(R.drawable.user_big)
                        .into(imageView);
            } else {
                imageView.setImageResource(R.drawable.user_big);
            }
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EMPLOYEE_CARD, employee);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
