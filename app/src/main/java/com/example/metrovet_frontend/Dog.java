package com.example.metrovet_frontend;

public class Dog {
    private String name;
    private String information;

    public Dog(String name, String information) {
        this.name = name;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return information;
    }
}
