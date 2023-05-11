package com.example.n3333.practice.module;

public class Movies {
    private String title, genre, year, checkpoint;

    public Movies() {

    }

    public Movies(String title, String genre, String year, String checkpoint) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.checkpoint = checkpoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCheckpoint() {
        return checkpoint;
    }
}
