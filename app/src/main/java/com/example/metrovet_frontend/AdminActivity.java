// AdminActivity.java
package com.example.metrovet_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class AdminActivity extends AppCompatActivity {

    private ImageView addButton;
    private ImageView deleteButton;
    private ImageView editButton;
    private ViewDogsFragment viewDogsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_main);

        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);
        editButton = findViewById(R.id.edit_button);

        viewDogsFragment = new ViewDogsFragment();

        // Load the ViewDogsFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, viewDogsFragment)
                .commit();

        TextView headerName = findViewById(R.id.header_name);
        headerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to ViewDogsFragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, viewDogsFragment)
                        .commit();

                // Make the buttons visible again when returning to the list
                setButtonsVisibility(View.VISIBLE);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the AddDogActivity
                Intent intent = new Intent(AdminActivity.this, AddDogActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle the visibility of delete_item_button in ViewDogsFragment
                viewDogsFragment.toggleDeleteItemButtonVisibility();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the EditDogActivity
                Intent intent = new Intent(AdminActivity.this, EditDogActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to set the visibility of buttons
    public void setButtonsVisibility(int visibility) {
        addButton.setVisibility(visibility);
        deleteButton.setVisibility(visibility);
        editButton.setVisibility(visibility);
    }
}
