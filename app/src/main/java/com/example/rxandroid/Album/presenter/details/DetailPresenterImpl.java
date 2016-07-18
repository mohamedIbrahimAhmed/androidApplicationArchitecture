package com.example.rxandroid.Album.presenter.details;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Album.model.Album;

import com.example.rxandroid.servecies.NetworkUtils;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

import static com.example.rxandroid.servecies.service.retrofit.withoutcache.AlbumService.PARAMS.ALBUM_ID;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public class DetailPresenterImpl implements DetailsPresenter, NetworkListener<Album> {
    private final DetailsView listener;
    private final BaseService service;
    private int AlbumId;

    public DetailPresenterImpl(DetailsView listener) {
        this.listener = listener;
        this.service = NetworkUtils.getAlbumService(this);

    }


    @Override
    public void loadAlbumDetail(int id) {
        AlbumId = id;
        service.execute(getParams());
    }


    private ArrayMap<String, String> getParams() {
        final ArrayMap<String, String> map = new ArrayMap<>(2);
        map.put(ALBUM_ID, String.valueOf(AlbumId));
        return map;

    }

    @Override
    public void onSuccess(Album album) {
        if (listener != null) listener.onAlbumLoaded(album);
    }

    @Override
    public void onError(NetworkException e) {
        if (e != null) listener.onFail(e.getMessage());
    }

    public void stopService() {
        service.cancel();
    }


}
