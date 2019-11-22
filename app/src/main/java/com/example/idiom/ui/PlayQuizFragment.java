package com.example.idiom.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idiom.R;
import com.example.idiom.model.Idioms;
import com.example.idiom.model.SaveIdioms;
import com.example.idiom.model.SaveViewModel;
import com.example.idiom.util.MyfirebaseInstance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayQuizFragment extends Fragment {
    private Button quiz_menu1, quiz_menu2, quiz_menu3, quiz_menu4;
    private TextView quizTextView;

    private CorrectDialog correctDialog;
    private IncorrectDialog incorrectDialog;

    private ArrayList<Idioms> makeQuizList;
    private ArrayList<Button> buttonList;
    private ArrayList<Idioms> optionList;

    private Idioms correctIdiom;

    private int correctIndex;
    private int solveCounter;

    private boolean isSavedQuiz = false;
    private SaveIdioms bundleIdioms;

    public PlayQuizFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_quiz, container, false);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setOnClickListener(new OptionListener(i));
        }

        if (getArguments() != null) {
            bundleIdioms = (SaveIdioms) getArguments().getSerializable("idi");
            isSavedQuiz = getArguments().getBoolean("flag");
            if (isSavedQuiz) { // 풀던 문제에서 문제 시작할때
                makeQuizList = bundleIdioms.quizList;
                Idioms idioms = settingCorrect();
                settingOptions(idioms);
            } else {
                // 문제풀기 버튼으로 문제 시작할때
                setQuizList();
                final Idioms idioms = settingCorrect();
                settingOptions(idioms);
            }
        }
    }

    private Idioms settingCorrect() {
        correctIdiom = getRandomQuestion();
        quizTextView.setText(correctIdiom.getId());
        correctIndex = (int) (Math.random() * 4);
        buttonList.get(correctIndex).setText(correctIdiom.getTitle());
        return correctIdiom;
    }

    private void settingOptions(Idioms idioms) {
        List<Idioms> incorrectIdiomList = getRandomQuestion(idioms);
        optionList = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (i != correctIndex) {
                optionList.add(incorrectIdiomList.get(index));
                buttonList.get(i).setText(incorrectIdiomList.get(index++).getTitle());
            } else {
                optionList.add(correctIdiom);
            }
        }
    }

    private Idioms getRandomQuestion() {
        int randomChoice = (int) (Math.random() * makeQuizList.size());
        return makeQuizList.get(randomChoice);
    }

    private List<Idioms> getRandomQuestion(Idioms idioms) {
        List<Idioms> titleList = new ArrayList<>();
        while (titleList.size() < 3) {
            int randomChoice = (int) (Math.random() * makeQuizList.size());
            if (!makeQuizList.get(randomChoice).equals(idioms)) {
                titleList.add(makeQuizList.get(randomChoice));
            }
        }
        return titleList;
    }

    private void init(View view) {
        solveCounter = 0;
        buttonList = new ArrayList<>();
        buttonList.add(quiz_menu1 = view.findViewById(R.id.quiz_menu_1));
        buttonList.add(quiz_menu2 = view.findViewById(R.id.quiz_menu_2));
        buttonList.add(quiz_menu3 = view.findViewById(R.id.quiz_menu_3));
        buttonList.add(quiz_menu4 = view.findViewById(R.id.quiz_menu_4));
        quizTextView = view.findViewById(R.id.quiz_textView);
        correctDialog = new CorrectDialog(requireContext(), correctListener);
        incorrectDialog = new IncorrectDialog(requireContext(), incorrectListener);
    }

    private void setQuizList() {
        makeQuizList = MyfirebaseInstance.idiomsList;
        Collections.shuffle(makeQuizList);
    }

    private View.OnClickListener correctListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Idioms idioms = settingCorrect();
            settingOptions(idioms);
            correctDialog.dismiss();
            solveCounter++;
        }
    };

    private View.OnClickListener incorrectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Idioms idioms = settingCorrect();
            settingOptions(idioms);
            incorrectDialog.dismiss();
            solveCounter++;
        }
    };

    @Override
    public void onDestroy() {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH시mm분ss초");
        String format_time2 = format2.format(System.currentTimeMillis());

        if (isSavedQuiz) {
            MyfirebaseInstance.getSaveInstance().child(bundleIdioms.key).setValue(new SaveIdioms(bundleIdioms.key, makeQuizList, solveCounter + bundleIdioms.solvedQuiz, (bundleIdioms.quizList.size() - solveCounter), format_time2));
            MyfirebaseInstance.getSavedQuiz();
        } else {
            String key = MyfirebaseInstance.getSaveInstance().push().getKey();
            if (key != null) {
                MyfirebaseInstance.getSaveInstance().child(key).setValue(new SaveIdioms(key, makeQuizList, solveCounter, (makeQuizList.size() - solveCounter), format_time2));
                MyfirebaseInstance.getSavedQuiz();
            }
            Objects.requireNonNull(SaveViewModel.saveIdiomsMutableLiveData.getValue()).clear();

        }

        super.onDestroy();
    }

    class OptionListener implements View.OnClickListener {

        private int idx;

        OptionListener(int idx) {
            this.idx = idx;
        }

        @Override
        public void onClick(View view) {
            if (idx == correctIndex) {
                correctDialog.show();
                correctDialog.settingDialogView(correctIdiom);
                makeQuizList.remove(correctIdiom);
            } else {
                incorrectDialog.show();
                incorrectDialog.settingDialogView(correctIdiom, optionList.get(idx));
                makeQuizList.remove(correctIdiom);
            }
        }
    }
}
