package com.example.myApplication.View.Email;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myApplication.ModelView.Email.EmailViewModel;
import com.example.navigationdrawer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EmailFragment extends Fragment {

    View rootView;
    TextView emailNotification;
    TextView emailAdd;
    TextView textOfEmail;
    TextView emailSubject;
    FloatingActionButton sendEmail;
    FloatingActionButton getEmail;
    EmailViewModel viewModel;
    ProgressBar progressBar;

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
    private void setUpLocalGUIVariables() {
        emailAdd=rootView.findViewById(R.id.emailAdd);
        textOfEmail=rootView.findViewById(R.id.textOfEmail);
        emailSubject=rootView.findViewById(R.id.emailSubject);
        sendEmail=rootView.findViewById(R.id.sendEmail);
        getEmail=rootView.findViewById(R.id.getEmail);
        progressBar=rootView.findViewById(R.id.progressBar);
        emailNotification=rootView.findViewById(R.id.emailNotification);
    }



    //add viewmodel
    private void addViewModel() {
        viewModel = new ViewModelProvider(this).get(EmailViewModel.class);
        viewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newEmail) {
                emailNotification.setVisibility(TextView.VISIBLE);
                emailNotification.setText(R.string.new_email_notification);
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