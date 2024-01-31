// AdminActivity.java
package com.example.metrovet_frontend;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_main);

        // Load the ViewDogsFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new ViewDogsFragment())
                .commit();
    }
}
