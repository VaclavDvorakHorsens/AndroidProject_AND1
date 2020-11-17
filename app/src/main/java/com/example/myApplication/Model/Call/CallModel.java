package com.example.myApplication.Model.Call;


import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CallModel {
    private List<PhoneNumber> numbers;
    private static CallModel  instance;
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("phoneNumbers");


//set up mom and dads phone number
    private CallModel (){
        numbers = new ArrayList<PhoneNumber>();
        if(numbers.size()==0) {
            savePhoneNumbersInDatabase();
        }
    }

    //return THIS
    public static CallModel getInstance(){
        if(instance == null)
            instance = new CallModel();
        return instance;
    }

    //return phone number
    public String getPhoneNumber(String phoneNumberName)
    {
        for(int i=0;i<=numbers.size();i++)
        {
            if(numbers.get(i).getName().equals(phoneNumberName))
            {
               return String.valueOf(numbers.get(i).getNumber());
            }
        }
        return null;
    }

    private void savePhoneNumbersInDatabase()
    {
        /*myRef.child("PhoneNumber1").setValue(new PhoneNumber("mom", 123456789));
        myRef.child("PhoneNumber2").setValue(new PhoneNumber("dad", 987654321));*/
    }


    public void removeEventListener() {
        myRef.removeEventListener(listener);

    }

    public void addValueEventListener() {
      myRef.addValueEventListener(listener);
    }


    private ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            PhoneNumber tempPhoneNumber;
            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
            {
                tempPhoneNumber=new PhoneNumber(String.valueOf(dataSnapshot1.child("name").getValue()),Integer.parseInt(String.valueOf(dataSnapshot1.child("number").getValue())));
                numbers.add(tempPhoneNumber);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };


}
