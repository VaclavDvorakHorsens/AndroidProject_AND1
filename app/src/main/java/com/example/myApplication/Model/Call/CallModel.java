package com.example.myApplication.Model.Call;

import java.util.ArrayList;
import java.util.List;

public class CallModel {
    private List<String> numbers;
    private static CallModel  instance;
    private boolean didYouCallMomLast;

    private CallModel (){
        List<String> numbers = new ArrayList<>();
    }

    public static CallModel getInstance(){
        if(instance == null)
            instance = new CallModel();

        return instance;
    }

    public String getPhoneNumber(int indexPhoneNumber)
    {
        if(didYouCallMomLast)
        {
            didYouCallMomLast=false;
        }
        else didYouCallMomLast=true;
        return numbers.get(indexPhoneNumber);
    }


    public boolean checkIfYouDidntCallThemAlready(boolean momOrDad) {

        if(momOrDad && didYouCallMomLast)
        {
            return true;
        }
        else if(!momOrDad && !didYouCallMomLast)
        {
            return true;
        }
        else return false;


    }
}
