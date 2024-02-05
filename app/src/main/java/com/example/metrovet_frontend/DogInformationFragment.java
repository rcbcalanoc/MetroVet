// DogInformationFragment.java
package com.example.metrovet_frontend;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class DogInformationFragment extends Fragment {

    // Constants for argument keys
    public static final String ARG_DOG_NAME = "dogName";
    public static final String ARG_DOG_TYPE = "dogType";
    public static final String ARG_DOG_INFO = "dogInfo";

    public DogInformationFragment() {
        // Required empty public constructor
    }

    public static DogInformationFragment newInstance(String dogName, String dogType, String dogInfo) {
        DogInformationFragment fragment = new DogInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DOG_NAME, dogName);
        args.putString(ARG_DOG_TYPE, dogType);
        args.putString(ARG_DOG_INFO, dogInfo);
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

        // Access the dog name, type, and info from arguments
        String dogName = getArguments().getString(ARG_DOG_NAME);
        String dogType = getArguments().getString(ARG_DOG_TYPE);
        String dogInfo = getArguments().getString(ARG_DOG_INFO);

        // Update the UI with dog information
        TextView dogInfoHeader = view.findViewById(R.id.dog_info_header);
        TextView dogInfoText = view.findViewById(R.id.dog_info_text);

        dogInfoHeader.setText(dogName + " - " + dogType);
        dogInfoText.setText(dogInfo);

        // Create a Typeface instance for your custom font
        Typeface customFont = ResourcesCompat.getFont(requireContext(), R.font.neue_frutiger_world_black);

        // Set click listener for the adopt button
        Button adoptButton = view.findViewById(R.id.adopt_button);
        adoptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the success message with a different font family
                dogInfoText.setTypeface(customFont);
                dogInfoText.setText(getString(R.string.success_message));
            }
        });
    }
}
