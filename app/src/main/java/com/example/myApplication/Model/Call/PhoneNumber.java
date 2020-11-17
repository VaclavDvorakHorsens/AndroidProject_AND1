package com.example.myApplication.Model.Call;


//class for storing data from firebase database
public class PhoneNumber {

    private String name;
    private int number;

    public PhoneNumber(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public PhoneNumber() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
