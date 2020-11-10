package com.example.myApplication.Model.Email;

public class Email {

    private String emailSender;
    private String emailMessage;


    public Email(String emailSender, String emailMessage)
    {
        this.emailMessage=emailMessage;
        this.emailSender=emailSender;
    }

    public String getEmailSender()
    {
        return emailSender;
    }

    public String getEmailMessage()
    {
        return emailMessage;
    }

}
