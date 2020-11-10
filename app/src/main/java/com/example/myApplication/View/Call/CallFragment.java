package com.example.myApplication.View.Call;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myApplication.Model.Diary.DiaryItem;
import com.example.myApplication.ModelView.Call.CallViewModel;
import com.example.navigationdrawer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class CallFragment extends Fragment {

    View rootView;
    FloatingActionButton makeCall;
    RadioButton radioMom;
    RadioButton radioDad;
    CoordinatorLayout callCoordinatorLayout;
    RadioGroup radioGroup;
    CallViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.call_activity, container, false);
        setUpLocalGUIVariables();
        addViewModel();
        setMakeCallListener();
        return rootView;
    }


    //set up local GUI variables
    private void setUpLocalGUIVariables() {
        makeCall = rootView.findViewById(R.id.makeCall);
        radioMom = rootView.findViewById(R.id.radioMom);
        radioDad = rootView.findViewById(R.id.radioDad);
        radioGroup = rootView.findViewById(R.id.radioGroup);
        callCoordinatorLayout = rootView.findViewById(R.id.callCoordinatorLayout);
    }


    //show a snack bar, user needs to confirm that they really want to make the call
    private void setMakeCallListener() {
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snackbar = Snackbar.make(callCoordinatorLayout, getActivity().getApplicationContext().getString(R.string.bother_parrents),
                        Snackbar.LENGTH_LONG)
                        .setAction(getActivity().getApplicationContext().getString(R.string.Yes), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                makeTheCall();
                            }
                        });
                snackbar.show();
                snackbar.setAnchorView(makeCall);
            }
        });
    }


    //make the call via intent, get phone number from model
    private void makeTheCall() {
        String telNumber = "";
        if (radioMom.isChecked()) {
            telNumber = viewModel.makeTheCall(0);
        } else if (radioDad.isChecked()) {
            telNumber = viewModel.makeTheCall(1);
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telNumber));startActivity(intent);

    }


    //add viewmodel
    private void addViewModel() {
        viewModel = new ViewModelProvider(this).get(CallViewModel.class);
    }

}

