package com.example.rxandroid.Post.presenter;

import android.os.Bundle;

import com.example.rxandroid.Main.model.Post;

/**
 * Created by mohamed.ibrahim on 7/18/2016.
 */

public interface AddPostPresenter {



    void cancelService();

    void onAttach();

    void onDetach();

    void saveState(Bundle b, Post post);

    void addPost(String userId, String title, String body);
}
