package com.example.rxandroid.Main.presenter;

import com.example.rxandroid.Main.model.Post;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public interface MainInterface {
    void loadPosts(List<Post> list);

    void onFail(String msg);

    void showNoInternetConnection() ;


}
