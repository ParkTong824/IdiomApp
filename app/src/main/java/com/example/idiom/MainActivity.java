package com.example.idiom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.idiom.model.SaveViewModel;
import com.example.idiom.ui.HomeFragment;
import com.example.idiom.util.MyfirebaseInstance;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyfirebaseInstance.settingData();
        MyfirebaseInstance.readSaveDatabase();
        initView();
    }

    private void initView() {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, homeFragment).commit();
    }

    @Override
    protected void onDestroy() {
        Objects.requireNonNull(SaveViewModel.saveIdiomsMutableLiveData.getValue()).clear();
        super.onDestroy();
    }
}
