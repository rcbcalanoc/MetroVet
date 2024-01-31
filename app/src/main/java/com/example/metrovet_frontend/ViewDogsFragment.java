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

// Existing imports...

public class ViewDogsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DogsAdapter adapter;

    // Define a constant for the argument key
    public static final String ARG_DOG_NAME = "dogName";

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
            public void onItemClick(String dogName) {
                // Handle item click, navigate to dog information, etc.
                showDogInformation(dogName);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private List<String> getDogsList() {
        // Create a list of dog items
        List<String> dogsList = new ArrayList<>();
        dogsList.add("Dog 1");
        dogsList.add("Dog 2");
        // Add more dog items as needed
        return dogsList;
    }

    private void showDogInformation(String dogName) {
        // Create a new fragment instance for dog information
        DogInformationFragment dogInformationFragment = new DogInformationFragment();

        // Create arguments bundle
        Bundle args = new Bundle();
        args.putString(ARG_DOG_NAME, dogName);

        // Set arguments to the fragment
        dogInformationFragment.setArguments(args);

        // Navigate to the dog information fragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, dogInformationFragment)
                .addToBackStack(null)  // Optional: Add to back stack for back navigation
                .commit();
    }
}
