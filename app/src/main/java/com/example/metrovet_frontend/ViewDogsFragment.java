// ViewDogsFragment.java
package com.example.metrovet_frontend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metrovet_frontend.model.Dog;
import com.example.metrovet_frontend.retrofit.MetroVetAPI;
import com.example.metrovet_frontend.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDogsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DogsAdapter adapter;
    private boolean deleteButtonClicked = false;

    public ViewDogsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_dogs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new DogsAdapter(new ArrayList<Dog>());

        // Set item click listener
        adapter.setOnItemClickListener(new DogsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Dog dog) {
                // Handle item click, navigate to dog information, etc.
                showDogInformation(dog);
            }
        });
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        fetchDogsData();
    }

    private void fetchDogsData() {
        RetrofitService retrofitService = new RetrofitService();
        MetroVetAPI metroVetAPI = retrofitService.getRetrofit().create(MetroVetAPI.class);
        Call<List<Dog>> call = metroVetAPI.getAllDogs();

        call.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update the adapter with the fetched data
                    //adapter.setDogsList(response.body());
                    //adapter.notifyDataSetChanged();
                    updateAdapterData(response.body());
                } else {
                    // Handle unsuccessful response
                    Logger.getLogger("API_ERROR", "Failed to fetch dogs data");
                }
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                // Handle failure
                Logger.getLogger(AddDogActivity.class.getName()).log(Level.SEVERE, "Error Occurred");
            }
        });

    }

    private void updateAdapterData(List<Dog> newDogsList) {
        // Update the adapter with the new data
        adapter.setDogsList(newDogsList);
    }


/*    private List<Dog> getDogsList() {
        // Create a list of dog items with information
        List<Dog> dogsList = new ArrayList<>();
        dogsList.add(new Dog("Dog 1", "Information about Dog 1"));
        dogsList.add(new Dog("Dog 2", "Information about Dog 2"));
        // Add more dog items as needed
        return dogsList;
    }*/

    private void showDogInformation(Dog dog) {
        // Create a new fragment instance for dog information
        DogInformationFragment dogInformationFragment = DogInformationFragment.newInstance(dog.getDogName(), dog.getDogDescription());

        // Toggle the visibility of delete_item_button if the activity is AdminActivity
        if (getActivity() instanceof AdminActivity) {
            toggleDeleteItemButtonVisibility();
        }

        // Hide the buttons in the activity when moving to dog information fragment
        if (getActivity() instanceof AdminActivity) {
            ((AdminActivity) requireActivity()).setButtonsVisibility(View.GONE);
        }

        // Navigate to the dog information fragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, dogInformationFragment)
                .addToBackStack(null)
                .commit();
    }

    // Method to toggle visibility of delete_item_button
    public void toggleDeleteItemButtonVisibility() {
        deleteButtonClicked = !deleteButtonClicked;

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View view = recyclerView.getChildAt(i);
                    ImageView deleteItemButton = view.findViewById(R.id.delete_item_button);
                    deleteItemButton.setVisibility(deleteButtonClicked ? View.VISIBLE : View.INVISIBLE);
                }
            }
        });
    }
}
