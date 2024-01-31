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

    // Constants for argument keys
    public static final String ARG_DOG_NAME = "dogName";
    public static final String ARG_DOG_INFORMATION = "dogInformation";

    public DogInformationFragment() {
        // Required empty public constructor
    }

    public static DogInformationFragment newInstance(String dogName, String dogInformation) {
        DogInformationFragment fragment = new DogInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DOG_NAME, dogName);
        args.putString(ARG_DOG_INFORMATION, dogInformation);
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

        // Access the dog name and information from arguments
        String dogName = getArguments().getString(ARG_DOG_NAME);
        String dogInformation = getArguments().getString(ARG_DOG_INFORMATION);

        // Update the UI with dog information
        TextView dogInfoHeader = view.findViewById(R.id.dog_info_header);
        TextView dogInfoText = view.findViewById(R.id.dog_info_text);

        dogInfoHeader.setText(dogName);
        dogInfoText.setText(dogInformation);
    }
}
