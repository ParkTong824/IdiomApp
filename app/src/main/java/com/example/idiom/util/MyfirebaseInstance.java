package com.example.idiom.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.idiom.model.Idioms;
import com.example.idiom.model.SaveIdioms;
import com.example.idiom.model.SaveViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyfirebaseInstance {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("idioms");
    private static DatabaseReference savedRef = database.getReference("saved");
    public static ArrayList<Idioms> idiomsList = new ArrayList<>();
    private static ArrayList<SaveIdioms> saveIdiomsArrayList = new ArrayList<>();

    public static void readSaveDatabase() {
        if (savedRef == null) {
            savedRef = database.getReference("saved");
            getSavedQuiz();
        }
        getSavedQuiz();
    }

    public static DatabaseReference getSaveInstance() {
        if (savedRef == null) {
            savedRef = database.getReference("saved");
        }
        return savedRef;
    }

    public static void settingData() {
        if (myRef == null) {
            myRef = database.getReference("idioms");
        }
        if (idiomsList.isEmpty()) {
            addIdiomListData();
        }
    }

    public static void deleteIdiom(String key) {
        savedRef.child(key).removeValue();
    }

    private static void addIdiomListData() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot idiomSnapshot : dataSnapshot.getChildren()) {
                    Idioms idioms = idiomSnapshot.getValue(Idioms.class);
                    idiomsList.add(idioms);
                }
                SaveViewModel.loadingDataSize.setValue(idiomsList.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FireBase Error","onCancelled"+databaseError.toString());
            }
        });
    }

    public static void getSavedQuiz() {
        savedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (saveIdiomsArrayList.isEmpty()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        SaveIdioms saveIdioms = snapshot.getValue(SaveIdioms.class);
                        saveIdiomsArrayList.add(saveIdioms);
                    }
                } else {
                    saveIdiomsArrayList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        SaveIdioms saveIdioms = snapshot.getValue(SaveIdioms.class);
                        saveIdiomsArrayList.add(saveIdioms);
                    }
                }
                SaveViewModel.saveIdiomsMutableLiveData.setValue(saveIdiomsArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Save Quiz Loaded","Fail-> "+databaseError.toString());
            }
        });
    }



}
