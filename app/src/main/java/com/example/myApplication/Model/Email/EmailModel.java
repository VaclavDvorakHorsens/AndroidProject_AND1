package com.example.myApplication.Model.Email;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Timer;
import java.util.TimerTask;

public class EmailModel {

    private static EmailModel instance;
    private MutableLiveData<String> newEmail=new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading=new MutableLiveData<>(false);

    //private constructor
    private EmailModel(){

    }

    //get instance of THIS
    public static EmailModel getInstance(){
        if(instance == null)
            instance = new EmailModel();
        return instance;
    }


    //retrieve email
    public void retrieveEmail()
    {
        isLoading.setValue(true);
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                newEmail.postValue("new value");
                isLoading.postValue(false);
            }
        },3000);
    }



    //return new email
    public LiveData<String> getEmail()
    {
        return newEmail;
    }


    //return if get email is still being retrieved
    public LiveData<Boolean> isLoading() {
            return isLoading;
    }
}





