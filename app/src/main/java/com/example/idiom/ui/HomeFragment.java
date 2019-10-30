package com.example.idiom.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idiom.R;
import com.example.idiom.network.RetrofitInstance;
import com.example.idiom.network.RetrofitService;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //TODO:서버요청보내기
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);

        Button startQuizButton = view.findViewById(R.id.start_quiz_button);
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new PlayQuizFragment()).addToBackStack(null).commit();
            }
        });
    }
}
