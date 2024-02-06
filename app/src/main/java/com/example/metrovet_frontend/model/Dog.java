package com.example.metrovet_frontend.model;


public class Dog {

    private int id;

    private String dogName;
    private String dogDescription;

    private String dogType;
    //private byte[] imageData; // Assuming you store the image as a byte array

    public Dog(int id, String dogName, String dogType, String dogDescription) {
        this.id = id;
        this.dogName = dogName;
        this.dogDescription = dogDescription;
        this.dogType = dogType;
    }

    public Dog(String dogName, String dogType, String dogDescription) {
        this.dogName = dogName;
        this.dogDescription = dogDescription;
        this.dogType = dogType;
    }

    public Dog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogDescription() {
        return dogDescription;
    }

    public void setDogDescription(String dogDescription) {
        this.dogDescription = dogDescription;
    }

    public String getDogType() {
        return dogType;
    }

    public void setDogType(String dogType) {
        this.dogType = dogType;
    }

}
