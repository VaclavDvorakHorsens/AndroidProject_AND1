package com.example.myApplication.ModelView.Call;

import androidx.lifecycle.ViewModel;
import com.example.myApplication.Model.Call.CallModel;

public class CallViewModel extends ViewModel {


    //constructor
    public void CallViewModel() {
    }


    //retrieve phone number
    public String makeTheCall(String phoneNumberName) {
        return CallModel.getInstance().getPhoneNumber(phoneNumberName);
    }

    public void addValueEventListener() {
        CallModel.getInstance().addValueEventListener();
    }

    public void removeEventListener() {
        CallModel.getInstance().removeEventListener();
    }

}