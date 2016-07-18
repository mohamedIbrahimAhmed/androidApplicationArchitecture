package com.example.rxandroid.Album.presenter.main;

import com.example.rxandroid.Album.model.Album;
import com.example.rxandroid.servecies.NetworkUtils;


import java.util.List;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public class AlbumPresenterImpl implements AlbumPresenter, NetworkListener<List<Album>> {

    private final AlbumView listener;
    private final BaseService service;


    public AlbumPresenterImpl(AlbumView listener) {
        this.listener = listener;
        service = NetworkUtils.getAlbumListService(this);
    }

    @Override
    public void loadAlbums() {
        service.execute(null);
    }

    @Override
    public void onSuccess(List<Album> alba) {
        if (listener != null) listener.onAlbumLoaded(alba);
    }

    @Override
    public void onError(NetworkException e) {
        if (e != null) listener.onFail(e.getMessage());
    }

    public void stopService() {
        service.cancel();
    }

}
