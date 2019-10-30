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
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first_bottom:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, homeFragment).commit();
                        break;
                    case R.id.second_bottom:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, studyFragment).commit();
                        break;
                }
                return false;
            }
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNav);
        containerView = findViewById(R.id.container_frame);
        homeFragment = new HomeFragment();
        studyFragment = new StudyFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, homeFragment).commit();
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
