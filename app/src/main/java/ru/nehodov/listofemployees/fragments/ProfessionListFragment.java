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
import android.widget.TextView;

import java.util.List;

import ru.nehodov.listofemployees.ProfessionListener;
import ru.nehodov.listofemployees.R;
import ru.nehodov.listofemployees.models.Profession;

public class ProfessionListFragment extends Fragment {

    private RecyclerView recycler;
    private ProfessionAdapter adapter;

    private ProfessionListener listener;

    public ProfessionListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profession_list, container, false);
        recycler = view.findViewById(R.id.professional_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new ProfessionAdapter();
        listener.getProfessions()
                .observe(requireActivity(), adapter::setProfessions);
        recycler.setAdapter(adapter);
        return view;
    }

    private class ProfessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Profession> professions;

        public ProfessionAdapter() {
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
            TextView nameTextView = holder.itemView.findViewById(R.id.profession_name);
            nameTextView.setText(profession.getName());
            holder.itemView.setOnClickListener(
                    view -> listener.selectProfession(profession));
        }

        @Override
        public int getItemCount() {
            if (professions == null) {
                return 0;
            } else {
                return professions.size();
            }
        }

        public void setProfessions(List<Profession> professions) {
            this.professions = professions;
            notifyDataSetChanged();
        }

    }

    public static ProfessionListFragment getInstance() {
        return new ProfessionListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            this.listener = (ProfessionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("Class %s must implement %s interface",
                    context.getClass().getSimpleName(),
                    listener.getClass().getSimpleName()
            ));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}
