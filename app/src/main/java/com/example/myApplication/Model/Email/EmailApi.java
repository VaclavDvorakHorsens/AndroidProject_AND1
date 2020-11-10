package com.example.myApplication.Model.Email;

import retrofit2.Call;
import retrofit2.http.GET;


public interface EmailApi {

    @GET("/getEmail/")
    Call<EmailResponse> getNewEmail();

}
