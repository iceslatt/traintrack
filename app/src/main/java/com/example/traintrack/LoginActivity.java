package com.example.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText password;

    public EditText username;
    android.widget.Button signin, register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signinButton);
        register = findViewById(R.id.registerButton);
        DB = new DBHelper(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                boolean userblank = user.equals("");
                boolean passblank = pass.equals("");


                Boolean checkuser_pass= DB.checkLogin(user, pass);

                if ( userblank == true || passblank == true ) {
                    Toast.makeText(LoginActivity.this, "Username or password field empty ", Toast.LENGTH_SHORT).show();
                }

                else{

                    // checks if the username and password are the correct values from the database.
                    if (checkuser_pass) {

                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();


                        android.content.Intent intent = new android.content.Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }

                    else {
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                Boolean checkuser= DB.checkUsername(user);


                // makes it impossible to make a user profile with an empty name and password
                boolean userblank = user.equals("");
                boolean passblank = pass.equals("");

                if ( userblank == true || passblank == true ) {
                    Toast.makeText(LoginActivity.this, "Username or password field empty ", Toast.LENGTH_SHORT).show();
                }

                else {

                    if (!checkuser){
                        Boolean insertUser = DB.insertData(user, pass);
                        if (insertUser) {
                            Toast.makeText(LoginActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this, "Enter your details and goals in the Settings page!", Toast.LENGTH_LONG).show();

                        }
                    }

                    else{
                        Toast.makeText(LoginActivity.this, "User already registered", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

}

