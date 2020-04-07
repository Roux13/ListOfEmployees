package ru.nehodov.listofemployees.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.stores.EmployeeList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeListFragment extends Fragment {

    private static final String PROFESSION_ID = "profession_id";

    private final EmployeeList employeeStore = EmployeeList.getInstance();

    private int professionId;

    private RecyclerView recycler;

    private EmployeeSelect employeeSelect;

    public EmployeeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employee_list, container, false);

        professionId = getArguments().getInt(PROFESSION_ID);
        recycler = view.findViewById(R.id.employee_list);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new EmployeeAdapter(
                employeeStore.getEmployeesByProfession(professionId),
                employeeSelect
        ));

        if (savedInstanceState != null) {
            professionId = savedInstanceState.getInt(PROFESSION_ID);
        }
        return view;
    }

    private class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final List<Employee> employees;

        private final EmployeeSelect employeeSelect;

        public EmployeeAdapter(List<Employee> employees, EmployeeSelect employeeSelect) {
            this.employees = employees;
            this.employeeSelect = employeeSelect;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.employee_item, parent, false);
            return new RecyclerView.ViewHolder(view) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final Employee employee = employees.get(position);
            holder.itemView.setBackgroundColor(this.getColor(position));
            TextView nameTextView = holder.itemView.findViewById(R.id.item_employee_name);
            TextView birthDateTextView = holder.itemView.findViewById(R.id.item_birth_date_employee);
            ImageView imageView = holder.itemView.findViewById(R.id.item_image_employee);

            nameTextView.setText(String.format("%s %s",
                    employee.getFirstName(), employee.getLastName()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            birthDateTextView.setText(dateFormat.format(employee.getBirthDate()));
            imageView.setImageResource(employee.getPhoto());

            holder.itemView.setOnClickListener(view -> employeeSelect.selectedEmployee(professionId, employee));
        }

        @Override
        public int getItemCount() {
            return employees.size();
        }

        private int getColor(int position) {
            int color = 0;
            if (position % 2 == 0) {
               color = getResources().getColor(R.color.grey_item);
            }
            if (position % 2 != 0) {
                color = getResources().getColor(R.color.white);
            }
            return color;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PROFESSION_ID, professionId);
    }



    public static EmployeeListFragment getInstance(int professionId) {
        Bundle args = new Bundle();
        args.putInt(PROFESSION_ID, professionId);
        EmployeeListFragment fragment = new EmployeeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.employeeSelect = (EmployeeSelect) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.employeeSelect = null;
    }

    public interface EmployeeSelect{
        void selectedEmployee(int professionId, Employee employee);
    }
}
