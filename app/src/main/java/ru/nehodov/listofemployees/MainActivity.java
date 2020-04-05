package ru.nehodov.listofemployees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import ru.nehodov.listofemployees.fragments.EmployeeListFragment;
import ru.nehodov.listofemployees.fragments.ProfessionListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host);
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.host) == null) {
            fm.beginTransaction()
                    .add(R.id.host, new ProfessionListFragment())
                    .commit();
        }
    }
}
