package com.example.myApplication.Model.Call;


import java.util.ArrayList;
import java.util.List;

public class CallModel {
    private List<String> numbers;
    private static CallModel  instance;

//set up mom and dads phone number
    private CallModel (){
        numbers = new ArrayList<>();
        numbers.add("123456789");
        numbers.add("987654321");
    }

    //return THIS
    public static CallModel getInstance(){
        if(instance == null)
            instance = new CallModel();
        return instance;
    }

    //return phone number
    public String getPhoneNumber(int indexPhoneNumber)
    {
        return numbers.get(indexPhoneNumber);
    }


}
