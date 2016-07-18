package com.example.rxandroid.Main.interactors;


import com.example.rxandroid.Main.model.Post;

import java.util.List;

import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/13/2016.
 */

public interface IMainInteractor {
    void getListOfPost(NetworkListener<List<Post>> listOfPost);

    void cancelService();
}
