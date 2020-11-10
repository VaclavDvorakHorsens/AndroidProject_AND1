package com.example.myApplication.Model.Email;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://985f809c-d18b-4cff-90c1-b3197d1dbec8.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static EmailApi emailApi = retrofit.create(EmailApi.class);

    public static EmailApi getEmailApi() {
        return emailApi;
    }
}