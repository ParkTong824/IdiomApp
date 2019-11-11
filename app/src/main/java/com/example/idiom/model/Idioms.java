package com.example.idiom.model;

import java.io.Serializable;

public class Idioms implements Serializable {
    private String id;
    private String mean;
    private String title;

    public Idioms(){

    }

    public Idioms(String id, String mean, String title) {
        this.id = id;
        this.mean = mean;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Idioms{" +
                "id='" + id + '\'' +
                ", mean='" + mean + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
