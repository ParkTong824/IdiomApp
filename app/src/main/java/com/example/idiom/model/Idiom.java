package com.example.idiom.model;

import java.io.Serializable;

public class Idiom implements Serializable {
    private String title;
    private String mean;
    private String korean;

    public Idiom(String title, String mean, String korean) {
        this.title = title;
        this.mean = mean;
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

    public String getTitle() {
        return title;
    }

    public String getMean() {
        return mean;
    }
}
