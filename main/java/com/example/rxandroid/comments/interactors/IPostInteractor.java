package com.example.rxandroid.comments.interactors;

import com.example.rxandroid.Main.model.Post;

import mohamedibrahim.networklib.services.common.NetworkListener;


/**
 * Created by mohamed.ibrahim on 7/12/2016.
 */

public interface IPostInteractor {
    void getPostDetails(int postId, NetworkListener<Post> postNetworkListener);

    void stopPostDetailsService();
}
