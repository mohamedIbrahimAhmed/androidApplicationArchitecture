package com.example.rxandroid.Post.presenter;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.Post.ui.PostView;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.common.validation.ValidationHelper;
import com.example.rxandroid.servecies.NetworkUtils;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/18/2016.
 */

public class PostPresenterImpl implements AddPostPresenter, NetworkListener<Post> {
    private final PostView view;
    private final BaseService service;


    public PostPresenterImpl(PostView view) {
        this.view = view;
        this.service = NetworkUtils.getAddPostService(this);
    }


    @Override
    public void cancelService() {
        service.cancel();
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void saveState(Bundle b, Post post) {

    }

    @Override
    public void addPost(String userId, String title, String body) {

        if (!validate(userId, title, body)) return;

        final ArrayMap<String, String> params = new ArrayMap<>();
        params.put(Keys.PARAMS.POST_USER_ID, String.valueOf(userId));
        params.put(Keys.PARAMS.POST_TITLE, title);
        params.put(Keys.PARAMS.POST_BODY, body);

        view.disablePostViews();
        view.showProgress();

        service.execute(params);
    }

    private boolean validate(String userId, String title, String body) {

        if (ValidationHelper.isEmpty(title)) {
            view.onTitleViewError("title is required");
            return false;
        }
        if (ValidationHelper.isEmpty(body)) {
            view.onBodyViewError("body is required");
            return false;
        }
        if (ValidationHelper.isEmpty(userId)) {
            view.onUserIdViewError("user id is required");
            return false;
        }


        if (!ValidationHelper.isNumber(userId)) {
            view.onUserIdViewError("user id must be number");
            return false;
        }

        return true;
    }

    @Override
    public void onSuccess(Post post) {
        if (view != null) {
            view.hideProgress();
            view.clearPostView();
            view.enablePostViews();


            view.onPostSubmittedSuccessfully(post.toString() + " \n submitted successfully");


        }
    }

    @Override
    public void onError(NetworkException e) {
        view.hideProgress();
        view.enablePostViews();
        view.onPostSubmittedFail(e.getMessage());
    }
}
