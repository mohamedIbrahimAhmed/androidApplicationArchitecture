package com.example.rxandroid.comments.interactors;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.servecies.NetworkUtils;

import java.util.List;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/12/2016.
 */

public class CommentInteractor implements ICommentInteractor, NetworkListener<List<Comment>> {


    private final BaseService commentListService;
    private NetworkListener<List<Comment>> listener;

    public CommentInteractor() {
        commentListService = NetworkUtils.CommentListService(this);

    }


    @Override
    public void getCommentList(int postId, NetworkListener<List<Comment>> listNetworkListener) {
        this.listener = listNetworkListener;
        commentListService.execute(getPostIdParam(postId));

    }


    @Override
    public void stopCommentListService() {
        if (commentListService != null && !commentListService.isCanceled()) {
            commentListService.cancel();
        }
    }


    private ArrayMap<String, String> getPostIdParam(int postId) {
        final ArrayMap<String, String> map = new ArrayMap<>();
        map.put(Keys.PARAMS.POST_ID, String.valueOf(postId));
        return map;

    }

    @Override
    public void onSuccess(List<Comment> list) {
        if (listener != null) listener.onSuccess(list);
    }

    @Override
    public void onError(NetworkException e) {
        if (listener != null) listener.onError(e);
    }


}
