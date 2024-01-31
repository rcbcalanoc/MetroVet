// ViewDogsFragment.java
package com.example.metrovet_frontend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewDogsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DogsAdapter adapter;

    // Define constants for the argument keys
    public static final String ARG_DOG_NAME = "dogName";
    public static final String ARG_DOG_INFORMATION = "dogInformation";

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
        adapter = new DogsAdapter(getDogsList());

        // Set item click listener
        adapter.setOnItemClickListener(new DogsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Dog dog) {
                // Handle item click, navigate to dog information, etc.
                showDogInformation(dog);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private List<Dog> getDogsList() {
        // Create a list of dog items with information
        List<Dog> dogsList = new ArrayList<>();
        dogsList.add(new Dog("Dog 1", "Information about Dog 1"));
        dogsList.add(new Dog("Dog 2", "Information about Dog 2"));
        // Add more dog items as needed
        return dogsList;
    }

    private void showDogInformation(Dog dog) {
        // Create a new fragment instance for dog information
        DogInformationFragment dogInformationFragment = DogInformationFragment.newInstance(dog.getName(), dog.getInformation());

        // Navigate to the dog information fragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, dogInformationFragment)
                .addToBackStack(null)
                .commit();
    }
}
