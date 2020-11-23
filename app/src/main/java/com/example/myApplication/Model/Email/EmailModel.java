package com.example.myApplication.Model.Email;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailModel {

    private static EmailModel instance;
    private MutableLiveData<Email> email;
    private MutableLiveData<Boolean> isLoading;

    //private constructor
    private EmailModel() {
        email = new MutableLiveData<>();
        isLoading= new MutableLiveData<>(false);
    }

    //get instance of THIS
    public static EmailModel getInstance() {
        if (instance == null)
            instance = new EmailModel();
        return instance;
    }


    //retrieve email
    public void retrieveEmail() {
        isLoading.setValue(true);
        //manual timer that makes the call longer so the loading can be shown on the screen via progress bar
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                EmailApi emailApi = ServiceGenerator.getEmailApi();
                Call<EmailResponse> call = emailApi.getNewEmail();
                call.enqueue(new Callback<EmailResponse>() {
                    @Override
                    public void onResponse(Call<EmailResponse> call, Response<EmailResponse> response) {
                        if (response.code() == 200) {
                            email.setValue(response.body().getEmail());
                        }
                    }
                    @Override
                    public void onFailure(Call<EmailResponse> call, Throwable t) {
                        Log.i("EmailDownloading", "Email downloading went wrong :(");
                    }
                });

                isLoading.postValue(false);
            }
        }, 3000);

    }


    //return new email
    public LiveData<Email> getEmail() {
        return email;
    }


    //return if get email is still being retrieved
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}





