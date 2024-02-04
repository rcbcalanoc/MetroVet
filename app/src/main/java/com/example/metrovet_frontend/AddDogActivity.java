// AddDogActivity.java
package com.example.metrovet_frontend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrovet_frontend.model.Dog;
import com.example.metrovet_frontend.retrofit.MetroVetAPI;
import com.example.metrovet_frontend.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDogActivity extends AppCompatActivity {


    private EditText dogNameEditText;
    private EditText dogTypeEditText;
    private EditText dogInfoEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog);


        // Initialize UI elements
        dogNameEditText = findViewById(R.id.add_fill_name);
        dogTypeEditText = findViewById(R.id.add_fill_type);
        dogInfoEditText = findViewById(R.id.add_fill_info);
        addButton = findViewById(R.id.add_dog_button);



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


        // Set click listener for the add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RetrofitService retrofitService = new RetrofitService();
                MetroVetAPI metroVetAPI = retrofitService.getRetrofit().create(MetroVetAPI.class);

                // Get data from EditText fields
                String dogName = dogNameEditText.getText().toString();
                String dogType = dogTypeEditText.getText().toString();
                String dogInfo = dogInfoEditText.getText().toString();


                Dog dog = new Dog();
                dog.setDogName(dogName);
                dog.setDogType(dogType);
                dog.setDogDescription(dogInfo);

                metroVetAPI.save(dog)
                        .enqueue(new Callback<Dog>() {
                            @Override
                            public void onResponse(Call<Dog> call, Response<Dog> response) {
                                Toast.makeText(AddDogActivity.this, "Saved!", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Dog> call, Throwable t) {
                                Toast.makeText(AddDogActivity.this, "Save Failed", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(AddDogActivity.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                            }
                        });
            }
        });

    }

}
