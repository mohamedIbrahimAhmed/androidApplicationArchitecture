package com.example.rxandroid.Main.presenter;

import android.os.Bundle;

import com.example.rxandroid.Main.model.Post;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public interface ListPresenter {
    void loadPosts(Bundle b);

    void saveState(Bundle b, List<Post> posts);

    void stopService();

    void onAttach();

    void onDetach();


}
