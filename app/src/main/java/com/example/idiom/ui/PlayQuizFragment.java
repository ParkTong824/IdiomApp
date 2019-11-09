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

public class PlayQuizFragment extends Fragment {
    Button quiz_menu1, quiz_menu2, quiz_menu3, quiz_menu4;

    public PlayQuizFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_quiz, container, false);
        quiz_menu1 = view.findViewById(R.id.quiz_menu_1);
        quiz_menu2 = view.findViewById(R.id.quiz_menu_2);
        quiz_menu3 = view.findViewById(R.id.quiz_menu_3);
        quiz_menu4 = view.findViewById(R.id.quiz_menu_4);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
