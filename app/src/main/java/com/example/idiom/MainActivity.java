package com.example.idiom;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idiom.model.Idioms;
import com.example.idiom.ui.HomeFragment;
import com.example.idiom.ui.StudyFragment;
import com.example.idiom.util.MyfirebaseInstance;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private StudyFragment studyFragment;
    private long backPressedTime = 0;
    static List<Idioms> dataList = new ArrayList<>();

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
        MyfirebaseInstance.getInstance();
//        MyfirebaseInstance.getInstance().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot idiomSnapshot : dataSnapshot.getChildren()) {
//                    Idioms idioms = idiomSnapshot.getValue(Idioms.class);
//                    dataList.add(idioms);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("Firebase Error","onCancelled"+databaseError.toString());
//            }
//        });

        bottomNavigationView = findViewById(R.id.bottomNav);
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
