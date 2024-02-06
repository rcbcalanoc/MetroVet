    package com.example.metrovet_frontend;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;
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

        private static final int EDIT_DOG_REQUEST_CODE = 1;

        private RecyclerView recyclerView;
        private DogsAdapter adapter;
        private DogViewModel dogViewModel;

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

            // Initialize ViewModel
            dogViewModel = new ViewModelProvider(requireActivity()).get(DogViewModel.class);

            recyclerView = view.findViewById(R.id.recyclerView);
            adapter = new DogsAdapter(new ArrayList<Dog>());

            // Set item click listener
            adapter.setOnItemClickListener(new DogsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Dog dog) {
                    // Handle item click, navigate to dog information, etc.
                    showDogInformation(dog);
                }

                @Override
                public void onEditButtonClick(Dog dog) {
                    // Set selected dog in ViewModel
                    dogViewModel.setSelectedDog(dog);

                    // Handle edit button click
                    handleEditButtonClick(dog);
                }

                @Override
                public void onDeleteButtonClick(Dog dog) {
                    // Call the API to delete the dog with the specified ID
                    deleteDog(dog.getId());
                }
            });

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
                        updateAdapterData(response.body());
                    } else {
                        // Handle unsuccessful response
                        Logger.getLogger("API_ERROR").log(Level.SEVERE, "Failed to fetch dogs data");
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
            adapter.setDogsList(newDogsList);
        }

        private void showDogInformation(Dog dog) {
            // Create a new fragment instance for dog information
            DogInformationFragment dogInformationFragment = DogInformationFragment.newInstance(dog.getDogName(), dog.getDogType(), dog.getDogDescription());

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

        private void handleEditButtonClick(Dog dog) {
            // Start the EditDogActivity with the selected dog's information
            Intent intent = new Intent(requireContext(), EditDogActivity.class);

            // Pass the dog ID to the EditDogActivity
            intent.putExtra(EditDogActivity.EXTRA_DOG_ID, dog.getId());

            startActivity(intent);
        }

        private void deleteDog(int dogId) {
            RetrofitService retrofitService = new RetrofitService();
            MetroVetAPI metroVetAPI = retrofitService.getRetrofit().create(MetroVetAPI.class);

            Call<Void> call = metroVetAPI.deleteDog(dogId);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Successfully deleted, update the UI
                        fetchDogsData();
                    } else {
                        // Handle unsuccessful response
                        Logger.getLogger("API_ERROR").log(Level.SEVERE, "Failed to delete dog");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle failure
                    Logger.getLogger(AddDogActivity.class.getName()).log(Level.SEVERE, "Error Occurred");
                }
            });
        }
    }
