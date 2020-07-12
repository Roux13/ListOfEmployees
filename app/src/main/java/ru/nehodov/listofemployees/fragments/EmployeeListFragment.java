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

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ru.nehodov.listofemployees.EmployeeListListener;
import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.models.Employee;
import ru.nehodov.listofemployees.models.Profession;

public class EmployeeListFragment extends Fragment {

    private static final String PROFESSION_KEY = "profession_id";

    private Profession profession;

    private RecyclerView recycler;
    private EmployeeAdapter adapter;

    private EmployeeListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employee_list, container, false);

        profession = (Profession) getArguments().getSerializable(PROFESSION_KEY);
        recycler = view.findViewById(R.id.employee_list);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new EmployeeAdapter();
        adapter.setEmployees(listener.getEmployees(profession));
        recycler.setAdapter(adapter);

        if (savedInstanceState != null) {
            profession = (Profession) savedInstanceState.getSerializable(PROFESSION_KEY);
        }
        return view;
    }

    private class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Employee> employees;

        public EmployeeAdapter() {
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.employee_item, parent, false);
            return new RecyclerView.ViewHolder(view) { };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final Employee employee = employees.get(position);
            TextView nameTextView = holder.itemView.findViewById(R.id.item_employee_name);
            TextView birthDateTextView =
                    holder.itemView.findViewById(R.id.item_birth_date_employee);
            ImageView imageView = holder.itemView.findViewById(R.id.item_image_employee);

            nameTextView.setText(String.format("%s %s",
                    employee.getFirstName(), employee.getLastName()));
            birthDateTextView.setText(employee.getBirthDate());
            if (employee.getPhoto() != null && !employee.getPhoto().equals("")) {
                Picasso.get().load(employee.getPhoto())
                        .placeholder(R.drawable.user_big)
                        .error(R.drawable.user_big)
                        .into(imageView);
            } else {
                imageView.setImageResource(R.drawable.user_big);
            }

            holder.itemView.setOnClickListener(
                    view -> listener.selectEmployee(profession, employee));
        }

        @Override
        public int getItemCount() {
            if (employees == null) {
                return 0;
            } else {
                return employees.size();
            }
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
            notifyDataSetChanged();
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PROFESSION_KEY, profession);
    }

    public static EmployeeListFragment getInstance(Profession profession) {
        Bundle args = new Bundle();
        args.putSerializable(PROFESSION_KEY, profession);
        EmployeeListFragment fragment = new EmployeeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (EmployeeListListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

}
