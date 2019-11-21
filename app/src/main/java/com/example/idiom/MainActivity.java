package com.example.idiom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.idiom.model.SaveViewModel;
import com.example.idiom.ui.HomeFragment;
import com.example.idiom.util.MyfirebaseInstance;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyfirebaseInstance.settingData();
        MyfirebaseInstance.getSavedRef();
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

    /*    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (intervalTime > 0 && intervalTime < 2000) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(this, "종료하려면 한번 더 눌러주세요", Toast.LENGTH_SHORT).show();
        }
    }*/
}
