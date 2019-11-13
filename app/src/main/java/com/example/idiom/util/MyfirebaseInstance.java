package com.example.idiom.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.idiom.model.Idioms;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyfirebaseInstance {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("idioms");
    public static ArrayList<Idioms> idiomsList = new ArrayList<>();

    public static DatabaseReference getInstance() {
        if (myRef == null) {
            myRef = database.getReference("idioms");
            addIdiomListData();
        }
        addIdiomListData();
        return myRef;
    }

    private static void addIdiomListData() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot idiomSnapshot : dataSnapshot.getChildren()) {
                    Idioms idioms = idiomSnapshot.getValue(Idioms.class);
                    idiomsList.add(idioms);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FireBase Error","onCancelled"+databaseError.toString());
            }
        });
    }
}
