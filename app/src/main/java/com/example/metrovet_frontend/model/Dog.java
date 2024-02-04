package com.example.metrovet_frontend.model;



public class Dog {

    private int id;

    private String dogName;
    private String dogDescription;

    //private byte[] imageData; // Assuming you store the image as a byte array

    public Dog(String dogName, String dogDescription, byte[] imageData) {
        this.dogName = dogName;
        this.dogDescription = dogDescription;
        //this.imageData = imageData;
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

    //public byte[] getImageData() {
     //   return imageData;
   // }

    //public void setImageData(byte[] imageData) {
    //    this.imageData = imageData;
   //}
}
