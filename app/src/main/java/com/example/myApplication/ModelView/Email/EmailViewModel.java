package com.example.myApplication.ModelView.Email;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myApplication.Model.Email.EmailModel;


public class EmailViewModel extends ViewModel {


    //constructor
    public void EmailViewModel() {
    }


    //return email
    public LiveData<String> getEmail()
    {
        return EmailModel.getInstance().getEmail();
    }


    //retrieve new email
    public void retrieveEmail()
    {
        EmailModel.getInstance().retrieveEmail();
    }


    //is still retrieveing email
    public LiveData<Boolean> checkIfLoading()
    {
        return EmailModel.getInstance().isLoading();
    }


    //send email
    public  boolean validateEmail(String emailSubject, String emailAddress, String emailBody)
    {
      if(isEmailValid(emailAddress))
      {
          return true;
      }
      else
      {
          return false;
      }
    }

    //validate email
    private boolean isEmailValid(String emailAddress)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailAddress.matches(emailPattern)&& emailAddress.length() > 0)
        {
            return true;
        }
        return false;
    }


}
