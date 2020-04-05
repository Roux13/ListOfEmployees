package ru.nehodov.listofemployees.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.models.Profession;
import ru.nehodov.listofemployees.stores.ProfessionList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessionListFragment extends Fragment {

    private ProfessionList professionStore = ProfessionList.getInstance();

    private RecyclerView recycler;

    public ProfessionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profession_list, container, false);
        recycler = view.findViewById(R.id.professional_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(new ProfessionAdapter());
        return view;
    }

    private class ProfessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Profession> professions;

        public ProfessionAdapter() {
            this.professions = professionStore.getProfessions();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.profession_item, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final Profession profession = professions.get(position);
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(getResources().getColor(R.color.grey_item));
            }
            if (position % 2 != 0) {
                holder.itemView.setBackgroundColor(getResources().getColor(R.color.white));
            }
            TextView nameTextView = holder.itemView.findViewById(R.id.profession_name);
            nameTextView.setText(profession.getName());
            holder.itemView.setOnClickListener(view -> {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putInt(EmployeeListFragment.PROFESSION_ID, profession.getId());
                Fragment fragment = new EmployeeListFragment();
                fragment.setArguments(args);
                fm.beginTransaction()
                        .addToBackStack("profession_list")
                        .replace(R.id.host, fragment)
                        .commit();
            });
        }

        @Override
        public int getItemCount() {
            return professions.size();
        }

    }




}
