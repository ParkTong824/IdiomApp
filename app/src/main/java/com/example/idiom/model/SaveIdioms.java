package com.example.idiom.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveIdioms implements Serializable {

    private ArrayList<Idioms> quizList;
    private int solvedQuiz;
    private int remainQuiz;
    private String cancelTime;

    public SaveIdioms(ArrayList<Idioms> quizList, int solvedQuiz, int remainQuiz, String cancelTime) {
        this.quizList = quizList;
        this.solvedQuiz = solvedQuiz;
        this.remainQuiz = remainQuiz;
        this.cancelTime = cancelTime;
    }

    @Override
    public String toString() {
        return "SaveIdioms{" +
                "quizList=" + quizList +
                ", solvedQuiz=" + solvedQuiz +
                ", remainQuiz=" + remainQuiz +
                ", cancelTime='" + cancelTime + '\'' +
                '}';
    }
}
