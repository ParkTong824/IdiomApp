package com.example.idiom.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveIdioms implements Serializable {

    public String key;
    public ArrayList<Idioms> quizList;
    public int solvedQuiz;
    public int remainQuiz;
    public String cancelTime;

    public SaveIdioms(){

    }

    public SaveIdioms(String key, ArrayList<Idioms> quizList, int solvedQuiz, int remainQuiz, String cancelTime) {
        this.key = key;
        this.quizList = quizList;
        this.solvedQuiz = solvedQuiz;
        this.remainQuiz = remainQuiz;
        this.cancelTime = cancelTime;
    }

    @Override
    public String toString() {
        return "SaveIdioms{" +
                "key='" + key + '\'' +
                ", quizList=" + quizList +
                ", solvedQuiz=" + solvedQuiz +
                ", remainQuiz=" + remainQuiz +
                ", cancelTime='" + cancelTime + '\'' +
                '}';
    }
}
