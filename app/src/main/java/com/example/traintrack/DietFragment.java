package com.example.traintrack;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class DietFragment extends Fragment {

    ProgressBar calorieBar;
    android.widget.Button addItem;
    ConstraintLayout foodEntryPopup;
    android.widget.Button cancelButton, confirmButton;
    EditText itemCalories, itemName;
    EditText foodLog;


    public DietFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_diet, container, false);



        calorieBar = view.findViewById(R.id.calorieBar);
        calorieBar.setMax(100);
        foodEntryPopup = view.findViewById(R.id.foodEntryPopup);
        itemCalories = view.findViewById(R.id.itemCalories);
        itemName = view.findViewById(R.id.itemName);

        foodLog = view.findViewById(R.id.foodLog);

        //Makes food log edittext uneditable by user.
        foodLog.setKeyListener(null);




        addItem = view.findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                foodEntryPopup.setVisibility(View.VISIBLE);
                System.out.print("BUTTON WORKS");

            }

        });

        cancelButton = view.findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                foodEntryPopup.setVisibility(View.INVISIBLE);
                itemCalories.setText("");
                itemName.setText("");
            }

        });
        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                // store user info from text fields
                foodEntryPopup.setVisibility(View.INVISIBLE);

                //adds entry to log
                StringBuilder sp = new StringBuilder(foodLog.getText()).append("\n"+itemName.getText()+"\t"+itemCalories.getText());
                int length = sp.length();
                int spaces = (37-length)/2;
                System.out.println("SPACES: "+ spaces);
                for (int i =0;i<spaces;i++){
                    sp.insert(0, " ");
                }
                System.out.println(sp.toString());
                foodLog.setText(sp.toString());

                // to clear fields after someone has confirmed a food entry
                itemCalories.setText("");
                itemName.setText("");

            }

        });


        return view;
    }
}