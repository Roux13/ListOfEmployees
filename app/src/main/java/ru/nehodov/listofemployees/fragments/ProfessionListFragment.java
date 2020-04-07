package ru.nehodov.listofemployees.fragments;

import android.content.Context;
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

    private ProfessionSelect professionSelect;

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
        recycler.setAdapter(new ProfessionAdapter(professionSelect));
        return view;
    }

    private class ProfessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final List<Profession> professions;

        private final ProfessionSelect professionSelect;

        public ProfessionAdapter(ProfessionSelect professionSelect) {
            this.professions = professionStore.getProfessions();
            this.professionSelect = professionSelect;
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
            holder.itemView.setBackgroundColor(getColor(position));
            TextView nameTextView = holder.itemView.findViewById(R.id.profession_name);
            nameTextView.setText(profession.getName());
            holder.itemView.setOnClickListener(
                    view -> professionSelect.selectedProfession(profession.getId()));
        }

        @Override
        public int getItemCount() {
            return professions.size();
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

    public static ProfessionListFragment getInstance() {
        return new ProfessionListFragment();
    }

    public interface ProfessionSelect {
        void selectedProfession(int professionId);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.professionSelect = (ProfessionSelect) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.professionSelect = null;
    }
}
