package com.example.rxandroid.comments.interactors;


import com.example.rxandroid.comments.model.Comment;

import java.util.List;

import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/12/2016.
 */

public interface ICommentInteractor {
    void getCommentList(int postId, NetworkListener<List<Comment>> listNetworkListener);

    void stopCommentListService();
}
