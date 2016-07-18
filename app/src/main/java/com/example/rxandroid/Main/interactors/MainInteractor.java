package com.example.rxandroid.Main.interactors;


import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.servecies.NetworkUtils;

import java.util.List;

import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/13/2016.
 */

public class MainInteractor implements IMainInteractor {
    private BaseService service;

    @Override
    public void getListOfPost(NetworkListener<List<Post>> listOfPost) {
        service = NetworkUtils.getForumListService(listOfPost);
        service.execute(null); // we don't need parameters
    }

    @Override
    public void cancelService() {
        if (service != null) service.cancel();
    }
}
