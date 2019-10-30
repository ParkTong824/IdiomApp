package com.example.idiom;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idiom.ui.HomeFragment;
import com.example.idiom.ui.StudyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout containerView;
    HomeFragment homeFragment;
    StudyFragment studyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first_bottom :
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, homeFragment).commit();
                        break;
                    case R.id.second_bottom :
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, studyFragment).commit();
                        break;
                }
                return false;
            }
        });
    }

    private void initView(){
        bottomNavigationView = findViewById(R.id.bottomNav);
        containerView = findViewById(R.id.container_frame);
        homeFragment = new HomeFragment();
        studyFragment = new StudyFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, homeFragment).commit();
    }
}
