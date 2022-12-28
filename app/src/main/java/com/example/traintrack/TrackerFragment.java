package com.example.traintrack;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TrackerFragment extends Fragment {

    android.widget.Button sendEmail;
    private EditText emailAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tracker, container, false);

        emailAddress = view.findViewById(R.id.emailField);
        Editable user_email = emailAddress.getText();


        sendEmail = view.findViewById(R.id.sendEmailButton);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(user_email.toString());
            }
        });


        return view;
    }

    public void sendMail(String email) {


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, email);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));

    }


    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }



}