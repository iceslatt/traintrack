package com.example.traintrack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traintrack.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    android.widget.Button signOut;
    android.widget.Button saveChanges;

    // user attributes

    EditText USERNAME, WEIGHT, AGE, GOAL,HEIGHT;
    DBHelper DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DB = new DBHelper(getContext());

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Sign out button which will redirect user to the Login Activity.
        signOut = view.findViewById(R.id.signoutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });

        // Save changes button which will save the user traits into the user profile database.

        USERNAME = view.findViewById(R.id.nameBox);
        Editable USER_NAME = USERNAME.getText();

        WEIGHT = view.findViewById(R.id.weightBox);
        Editable USER_WEIGHT = WEIGHT.getText();

        AGE = view.findViewById(R.id.ageBox);
        Editable USER_AGE = AGE.getText();

        GOAL = view.findViewById(R.id.goalBox);
        Editable USER_GOAL = GOAL.getText();

        HEIGHT = view.findViewById(R.id.heightBox);
        Editable USER_HEIGHT = HEIGHT.getText();


        saveChanges = view.findViewById(R.id.savechangesButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                // TO DO ADD USERNAME AT THE START OF INSERTUSERDATA()

                Boolean successful = DB.insertUserData(
                        USER_NAME.toString(),
                        Integer.parseInt(USER_WEIGHT.toString() ),
                        Integer.parseInt(USER_AGE.toString() ),
                        Integer.parseInt(USER_GOAL.toString() ),
                        Integer.parseInt(USER_HEIGHT.toString() )
                        );

                if (successful){
                    System.out.println("Successfully added");
                }


                // 66.5 + ( 13.76 × weight in kg ) + ( 5.003 × height in cm ) – ( 6.755 × age in years )

            }

        });

        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }



}



