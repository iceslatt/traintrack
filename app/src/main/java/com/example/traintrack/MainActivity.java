package com.example.traintrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.Settings;

import com.example.traintrack.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().findItem(R.id.Home1).setChecked(true);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            // Switch Case which will replace the previous fragment upon clicking a new one.
            switch (item.getItemId()){

                case R.id.Home1:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.Diet:
                    replaceFragment(new DietFragment());
                    break;
                case R.id.Settings:
                    replaceFragment(new SettingsFragment());
                    break;
                case R.id.Tracker:
                    replaceFragment(new TrackerFragment());
                    break;
            }

            return true;
        });

    }


    // Method which will replace the previous fragment.
    private void replaceFragment(androidx.fragment.app.Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}