package com.example.metrovet_frontend;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main); // Set the content view to user_activity_main.xml

        // Load the ViewDogsFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new ViewDogsFragment())
                .commit();
    }
}
