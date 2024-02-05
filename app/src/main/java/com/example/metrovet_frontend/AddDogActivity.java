// AddDogActivity.java
package com.example.metrovet_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button addDogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog);

        dogNameEditText = findViewById(R.id.add_fill_name);
        dogTypeEditText = findViewById(R.id.add_fill_type);
        dogInfoEditText = findViewById(R.id.add_fill_info);
        addDogButton = findViewById(R.id.add_dog_button);

        addDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract data from input fields
                String dogName = dogNameEditText.getText().toString().trim();
                String dogType = dogTypeEditText.getText().toString().trim();
                String dogInfo = dogInfoEditText.getText().toString().trim();

                // Create a new Dog instance
                Dog newDog = new Dog(dogName, dogType, dogInfo);

                // Call the API to add a new dog
                addDogToAPI(newDog);

                // Navigate to DogInformationFragment with dogInfo
                showDogInformation(dogName, dogType, dogInfo);
            }
        });
    }

    private void addDogToAPI(Dog newDog) {
        RetrofitService retrofitService = new RetrofitService();
        MetroVetAPI metroVetAPI = retrofitService.getRetrofit().create(MetroVetAPI.class);

        Call<Dog> call = metroVetAPI.save(newDog);

        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Dog added successfully
                    Toast.makeText(AddDogActivity.this, "Dog added successfully", Toast.LENGTH_SHORT).show();

                    // Relaunch AdminActivity
                    Intent intent = new Intent(AddDogActivity.this, AdminActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    finish(); // Close the activity after relaunching AdminActivity
                } else {
                    // Handle unsuccessful response
                    Logger.getLogger("API_ERROR").log(Level.SEVERE, "Failed to add a new dog");
                    Toast.makeText(AddDogActivity.this, "Failed to add a new dog", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                // Handle failure
                Logger.getLogger(AddDogActivity.class.getName()).log(Level.SEVERE, "Error occurred");
                Toast.makeText(AddDogActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to show DogInformationFragment
    private void showDogInformation(String dogName, String dogType, String dogInfo) {
        // Create a new fragment instance for dog information
        DogInformationFragment dogInformationFragment = DogInformationFragment.newInstance(dogName, dogType, dogInfo);

        // Navigate to the dog information fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, dogInformationFragment)
                .addToBackStack(null)
                .commit();
    }
}
