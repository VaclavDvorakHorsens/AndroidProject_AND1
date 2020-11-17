package com.example.myApplication.Model.Call;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class CallModel {
    private List<PhoneNumber> phoneNumbers;
    private static CallModel instance;
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("phoneNumbers");


    //set up mom and dads phone number
    private CallModel() {
        phoneNumbers = new ArrayList<PhoneNumber>();
        if (phoneNumbers.size() == 0) {
            savePhoneNumbersInDatabase();
        }
    }

    //return THIS
    public static CallModel getInstance() {
        if (instance == null)
            instance = new CallModel();
        return instance;
    }

    //return phone number
    public String getPhoneNumber(String phoneNumberName) {
        for (int i = 0; i <= phoneNumbers.size(); i++) {
            if (phoneNumbers.get(i).getName().equals(phoneNumberName)) {
                return String.valueOf(phoneNumbers.get(i).getNumber());
            }
        }
        return null;
    }

    //if there is no data in firebase database, then this method can set it
    //the data is there at the moment, so its commented out
    private void savePhoneNumbersInDatabase() {
        /*myRef.child("PhoneNumber1").setValue(new PhoneNumber("mom", 123456789));
        myRef.child("PhoneNumber2").setValue(new PhoneNumber("dad", 987654321));*/
    }


    //add listener on DatabaseReference
    public void removeEventListener() {
        myRef.removeEventListener(listener);
    }

    //remove listener on DatabaseReference
    public void addValueEventListener() {
        myRef.addValueEventListener(listener);
    }

    //set listener on data in firebase database
    private ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            PhoneNumber tempPhoneNumber;
            //if internal list of phone numbers is empty
            if (phoneNumbers.size() == 0) {
                //add phone numbers to internal list of phone numbers
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    tempPhoneNumber = new PhoneNumber(String.valueOf(dataSnapshot1.child("name").getValue()), Integer.parseInt(String.valueOf(dataSnapshot1.child("number").getValue())));
                    phoneNumbers.add(tempPhoneNumber);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };

}
