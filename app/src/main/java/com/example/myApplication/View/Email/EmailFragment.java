package com.example.myApplication.View.Email;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myApplication.Model.Email.Email;
import com.example.myApplication.ModelView.Email.EmailViewModel;
import com.example.navigationdrawer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EmailFragment extends Fragment {

    View rootView;
    TextView emailNotificationSender;
    TextView emailNotificationText;
    TextView emailAdd;
    TextView textOfEmail;
    TextView emailSubject;
    FloatingActionButton sendEmail;
    FloatingActionButton getEmail;
    EmailViewModel viewModel;
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.email_activity, container, false);
        //set up variables and models
        addViewModel();
        setUpLocalGUIVariables();

        setUpEmailListener();
        setGetEmailListener();
        return rootView;
    }





    //set up send new email listener
    private void setUpEmailListener() {
        sendEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try
                {
                    if(!viewModel.validateEmail(emailSubject.getText().toString(),emailAdd.getText().toString(),textOfEmail.getText().toString()))
                    {
                        Toast toast=Toast.makeText(getActivity().getApplicationContext(),getActivity().getApplicationContext().getString(R.string.email_address_validation),Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,150);
                        toast.show();
                    }
                    else
                    {
                        SendEmail(emailSubject.getText().toString(),emailAdd.getText().toString(),textOfEmail.getText().toString());
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity().getApplicationContext(),getActivity().getApplicationContext().getString(R.string.error_message),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    //set GetEmail button listener
    private void setGetEmailListener()
    {
        getEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkNewEmail();
            }
        });

    }

    //send email
    private void SendEmail(String emailSubject, String emailAddress, String emailBody) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
            intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
            intent.putExtra(Intent.EXTRA_TEXT, emailBody);
            startActivity(intent);
    }


    //set up local GUI variables

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUpLocalGUIVariables() {
        emailAdd=rootView.findViewById(R.id.emailAdd);
        textOfEmail=rootView.findViewById(R.id.textOfEmail);
        emailSubject=rootView.findViewById(R.id.emailSubject);
        sendEmail=rootView.findViewById(R.id.sendEmail);
        getEmail=rootView.findViewById(R.id.getEmail);
        progressBar=rootView.findViewById(R.id.progressBar);
        emailNotificationSender=rootView.findViewById(R.id.emailNotificationSender);
        emailNotificationText=rootView.findViewById(R.id.emailNotificationText);
    }



    //add viewmodel and listener on new email api
    private void addViewModel() {
        viewModel = new ViewModelProvider(this).get(EmailViewModel.class);
        viewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<Email>() {
            @Override
            public void onChanged(Email newEmail) {
                emailNotificationSender.setVisibility(TextView.VISIBLE);
                /*emailNotification.setText(R.string.new_email_notification+newEmail.getEmailSender());*/
                emailNotificationSender.setText(R.string.new_email_notification);
                emailNotificationSender.append("\n"+newEmail.getEmailSender());
                String emailMessage=newEmail.getEmailMessage();
                if(emailMessage.length() > 50) {
                   emailMessage = emailMessage.substring(0, 50)+getActivity().getApplicationContext().getString(R.string.go_to_emailBox);
                }
                emailNotificationText.setText(emailMessage);
                emailNotificationText.setVisibility(TextView.VISIBLE);

            }
        });
        viewModel.checkIfLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
              int visibility = isLoading ? View.VISIBLE: View.INVISIBLE;
              progressBar.setVisibility(visibility);
            }
        });
    }


    //check new email
private void checkNewEmail()
    {
        viewModel.retrieveEmail();
    }



}