package com.example.idiom.model;

public class Idiom {
    private String title;
    private String mean;

    public Idiom(String title, String mean) {
        this.title = title;
        this.mean = mean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
