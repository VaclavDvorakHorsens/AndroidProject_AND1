package com.example.myApplication.ModelView.Email;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.myApplication.Model.Email.Email;
import com.example.myApplication.Model.Email.EmailModel;


public class EmailViewModel extends ViewModel {


    //constructor
    public void EmailViewModel() {
    }


    //return email
    public LiveData<Email> getEmail()
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
    public boolean validateEmail(String emailSubject, String emailAddress, String emailBody)
    {
      if(isEmailAddressValid(emailAddress) && !isEmailSubjectEmpty(emailSubject))
      {
          return true;
      }
      else
      {
          return false;
      }
    }

    //validate email subject
    private boolean isEmailSubjectEmpty(String emailSubject) {
        if(emailSubject.equals(""))
        {
            return true;
        }
        return false;
    }

    //validate email address
    private boolean isEmailAddressValid(String emailAddress)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailAddress.matches(emailPattern)&& emailAddress.length() > 0)
        {
            return true;
        }
        return false;
    }


}
