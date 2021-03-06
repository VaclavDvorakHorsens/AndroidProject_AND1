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

    //add listener on DatabaseReference in Model
    public void addValueEventListener() {
        CallModel.getInstance().addValueEventListener();
    }

    //remove listener on DatabaseReference in Model
    public void removeEventListener() {
        CallModel.getInstance().removeEventListener();
    }

}