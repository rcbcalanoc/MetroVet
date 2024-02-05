package com.example.metrovet_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.metrovet_frontend.model.Dog;
import com.example.metrovet_frontend.retrofit.MetroVetAPI;
import com.example.metrovet_frontend.retrofit.RetrofitService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDogActivity extends AppCompatActivity {

    public static final String EXTRA_DOG_ID = "dogId";

    private EditText dogNameEditText;
    private EditText dogTypeEditText;
    private EditText dogInfoEditText;
    private Button updateDogButton;
    private DogViewModel dogViewModel;

    private int dogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_dog);

        // Get the dog ID from the intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_DOG_ID)) {
            dogId = intent.getIntExtra(EXTRA_DOG_ID, -1);

            // Log the dog ID for debugging
            Log.d("EditDogActivity", "Editing Dog with ID: " + dogId);
        } else {
            Toast.makeText(this, "Invalid Dog Data", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        dogNameEditText = findViewById(R.id.edit_fill_name);
        dogTypeEditText = findViewById(R.id.edit_fill_type);
        dogInfoEditText = findViewById(R.id.edit_fill_info);
        updateDogButton = findViewById(R.id.add_dog_button);

        // Initialize ViewModel
        dogViewModel = new ViewModelProvider(this).get(DogViewModel.class);

        // Fetch the selected dog's data using the ID
        fetchDogData();

        // Set click listener for the update button
        updateDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract data from input fields
                String dogName = dogNameEditText.getText().toString().trim();
                String dogType = dogTypeEditText.getText().toString().trim();
                String dogInfo = dogInfoEditText.getText().toString().trim();

                // Create a new Dog instance with updated information
                Dog updatedDog = new Dog(dogName, dogType, dogInfo);
                updatedDog.setId(dogId);

                // Call the API to update the dog
                updateDogInAPI(updatedDog);
            }
        });

        // Set click listener for the header_name TextView
        TextView headerName = findViewById(R.id.header_name);
        headerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to ViewDogsFragment
                finish(); // This will close the current activity and go back to the previous one
            }
        });
    }

    private void fetchDogData() {
        RetrofitService retrofitService = new RetrofitService();
        MetroVetAPI metroVetAPI = retrofitService.getRetrofit().create(MetroVetAPI.class);

        // Update the Call type to Call<Dog>
        Call<Dog> call = metroVetAPI.getDog(dogId);

        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Get the selected dog
                    Dog selectedDog = response.body();
                    updateUI(selectedDog);
                } else {
                    // Handle unsuccessful response
                    Logger.getLogger("API_ERROR").log(Level.SEVERE, "Failed to fetch dog data");
                    Toast.makeText(EditDogActivity.this, "Failed to fetch dog data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                // Handle failure
                Logger.getLogger(EditDogActivity.class.getName()).log(Level.SEVERE, "Error Occurred");
                Toast.makeText(EditDogActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void updateUI(Dog selectedDog) {
        // Populate EditText fields with selected dog's data
        dogNameEditText.setText(selectedDog.getDogName());
        dogTypeEditText.setText(selectedDog.getDogType());
        dogInfoEditText.setText(selectedDog.getDogDescription());
    }

    private void updateDogInAPI(Dog updatedDog) {
        RetrofitService retrofitService = new RetrofitService();
        MetroVetAPI metroVetAPI = retrofitService.getRetrofit().create(MetroVetAPI.class);

        Call<Dog> call = metroVetAPI.updateDog(updatedDog.getId(), updatedDog);

        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Dog updated successfully
                    Toast.makeText(EditDogActivity.this, "Dog updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after a successful update
                } else {
                    // Handle unsuccessful response
                    handleUnsuccessfulUpdate(response);
                }
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                // Handle failure
                Logger.getLogger(EditDogActivity.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                Toast.makeText(EditDogActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleUnsuccessfulUpdate(Response<Dog> response) {
        if (response.code() == 400) {
            // Bad Request - Validation error
            try {
                String errorMessage = response.errorBody().string();
                Logger.getLogger("API_ERROR").log(Level.SEVERE, "Validation error: " + errorMessage);
                Toast.makeText(EditDogActivity.this, "Validation error: " + errorMessage, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(EditDogActivity.this, "Failed to update the dog. Please try again.", Toast.LENGTH_SHORT).show();
            }
        } else if (response.code() == 404) {
            // Not Found - Dog with the given ID not found
            Logger.getLogger("API_ERROR").log(Level.SEVERE, "Dog not found");
            Toast.makeText(EditDogActivity.this, "Dog not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Other errors
            Logger.getLogger("API_ERROR").log(Level.SEVERE, "Failed to update the dog. Unknown error");
            Toast.makeText(EditDogActivity.this, "Failed to update the dog. Unknown error", Toast.LENGTH_SHORT).show();
        }
    }
}
