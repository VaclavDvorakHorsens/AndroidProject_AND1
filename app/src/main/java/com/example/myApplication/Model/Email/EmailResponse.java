package com.example.myApplication.Model.Email;

public class EmailResponse {


    private String emailSender;
    private String emailMessage;

    public Email getEmail(){
        return new Email(emailSender,emailMessage);
    }

}
