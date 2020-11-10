package com.example.myApplication.ModelView.Call;

import androidx.lifecycle.ViewModel;
import com.example.myApplication.Model.Call.CallModel;

public class CallViewModel extends ViewModel {


    //constructor
    public void CallViewModel() {
    }


    //retrieve phone number
    public String makeTheCall(int indexPhoneNumber) {
        return CallModel.getInstance().getPhoneNumber(indexPhoneNumber);
    }

}