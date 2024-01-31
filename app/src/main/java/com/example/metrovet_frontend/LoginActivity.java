package com.example.metrovet_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Find buttons by their IDs
        Button userButton = findViewById(R.id.go_User);
        Button adminButton = findViewById(R.id.go_admin);

        // Set click listeners for the buttons
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for the user button
                navigateToUserActivity();
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for the admin button
                navigateToAdminActivity();
            }
        });
    }

    // Method to navigate to UserActivity
    private void navigateToUserActivity() {
        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
        startActivity(intent);
        finish(); // Optional: Close the LoginActivity if you don't want it in the back stack
    }

    // Method to navigate to AdminActivity
    private void navigateToAdminActivity() {
        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
        startActivity(intent);
        finish(); // Optional: Close the LoginActivity if you don't want it in the back stack
    }
}
