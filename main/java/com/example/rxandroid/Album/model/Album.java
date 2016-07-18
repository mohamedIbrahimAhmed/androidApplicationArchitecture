package com.example.rxandroid.Album.model;
/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public class Album {
    private int userId;
    private int id;
    private String title;


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
