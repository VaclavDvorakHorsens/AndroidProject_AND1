package com.example.myApplication.ModelView.Call;

import androidx.lifecycle.ViewModel;

import com.example.myApplication.Model.Call.CallModel;

public class CallViewModel extends ViewModel
    {


        //constructor
        public void CallViewModel() {
    }




        //retrieve phone number
        public String makeTheCall(int indexPhoneNumber)
        {
            return CallModel.getInstance().getPhoneNumber(indexPhoneNumber);
        }


        //calling the same number two times in a row check
        public boolean checkIfYouDidntCallThemAlready(boolean momOrDad) {
            return CallModel.getInstance().checkIfYouDidntCallThemAlready(momOrDad);
        }
    }