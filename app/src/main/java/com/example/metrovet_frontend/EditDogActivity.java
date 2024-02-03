// AddDogActivity.java
package com.example.metrovet_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditDogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_dog);

        // Get references to UI elements
        TextView headerName = findViewById(R.id.header_name);

        // Set click listener for the header_name TextView
        headerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to AdminActivity
                finish(); // This will close the current activity and go back to the previous one
            }
        });
    }
}
