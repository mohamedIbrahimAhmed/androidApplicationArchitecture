package com.example.rxandroid.Post.ui;

/**
 * Created by mohamed.ibrahim on 7/18/2016.
 */

public interface PostView {

    void showProgress();

    void hideProgress();

    void disablePostViews();

    void enablePostViews();


    void onTitleViewError(String message);

    void onBodyViewError(String message);

    void onUserIdViewError(String message);


    void onPostSubmittedSuccessfully(String message);

    void clearPostView();

    void onPostSubmittedFail(String message);
}
