package com.pienkowska.swim.musicplayer;

public class Song {
    private int id;
    private String author;
    private String title;

    public Song(int id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

}
