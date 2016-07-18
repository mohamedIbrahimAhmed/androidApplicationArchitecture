package com.example.rxandroid.comments.model;

import android.graphics.Color;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public class Comment {
    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;


    public Comment(int postId, int id, String name, String email, String body) {

        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Comment() {
    }


    public int getBackgroundColor() {
        if (id % 2 == 0) return Color.RED;
        return Color.BLUE;
    }


}
