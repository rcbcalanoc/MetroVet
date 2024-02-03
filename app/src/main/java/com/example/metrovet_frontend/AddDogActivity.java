// AddDogActivity.java
package com.example.metrovet_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddDogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog);

        // Get references to UI elements
        TextView headerName = findViewById(R.id.header_name);
        EditText addFillName = findViewById(R.id.add_fill_name);
        EditText addFillType = findViewById(R.id.add_fill_type);
        EditText addFillInfo = findViewById(R.id.add_fill_info);
        Button addButton = findViewById(R.id.button);

        // Set click listener for the header_name TextView
        headerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to AdminActivity
                finish(); // This will close the current activity and go back to the previous one
            }
        });

        // Add your logic for the addButton click listener if needed
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your logic for the button click (e.g., saving dog information)
            }
        });
    }
}
