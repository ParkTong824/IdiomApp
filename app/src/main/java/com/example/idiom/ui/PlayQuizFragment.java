package com.example.idiom.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idiom.R;
import com.example.idiom.model.Quiz;

public class PlayQuizFragment extends Fragment {
    Button quiz_menu1, quiz_menu2, quiz_menu3, quiz_menu4;

    private CustomDialog customDialog;

    public PlayQuizFragment() {
    }

    public interface AnswerItemOnClickListener {
        void answerOnClick(Quiz quiz);
    }
    private PlayQuizFragment.AnswerItemOnClickListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_quiz, container, false);
        //보기 선택할때 마다 맞는지 틀렸는지 dialog 에 표현해야됨
        quiz_menu1 = view.findViewById(R.id.quiz_menu_1);
        quiz_menu2 = view.findViewById(R.id.quiz_menu_2);
        quiz_menu3 = view.findViewById(R.id.quiz_menu_3);
        quiz_menu4 = view.findViewById(R.id.quiz_menu_4);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        customDialog = new CustomDialog(requireContext(), dialogListener);
        customDialog.show();
    }


    private View.OnClickListener dialogListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(requireContext(), "다음문제",Toast.LENGTH_SHORT).show();
            customDialog.dismiss();
        }
    };
}
