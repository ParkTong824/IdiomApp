package com.example.idiom.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SaveViewModel extends ViewModel {

    public static MutableLiveData<ArrayList<SaveIdioms>> saveIdiomsMutableLiveData = new MutableLiveData<>();

    public SaveViewModel() {

    }
}
