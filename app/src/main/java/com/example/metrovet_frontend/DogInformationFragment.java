// DogInformationFragment.java
package com.example.metrovet_frontend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DogInformationFragment extends Fragment {
    // Constant for argument key
    public static final String ARG_DOG_NAME = "dogName";

    public DogInformationFragment() {
        // Required empty public constructor
    }

    public static DogInformationFragment newInstance(String dogName) {
        DogInformationFragment fragment = new DogInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DOG_NAME, dogName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dog_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Access the dog name from arguments
        String dogName = getArguments().getString(ARG_DOG_NAME);

        // Update the UI with dog information
        // You can access views and set data here
        TextView dogInfoHeader = view.findViewById(R.id.dog_info_header);
        dogInfoHeader.setText(dogName);

        // Update other views accordingly
    }
}
