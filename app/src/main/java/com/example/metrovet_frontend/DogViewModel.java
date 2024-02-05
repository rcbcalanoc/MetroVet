package com.example.metrovet_frontend;

import androidx.lifecycle.ViewModel;

import com.example.metrovet_frontend.model.Dog;

public class DogViewModel extends ViewModel {

    private Dog selectedDog;

    public Dog getSelectedDog() {
        return selectedDog;
    }

    public void setSelectedDog(Dog dog) {
        selectedDog = dog;
    }
}
