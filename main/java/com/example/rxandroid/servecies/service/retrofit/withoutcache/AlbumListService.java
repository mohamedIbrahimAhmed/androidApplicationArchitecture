package com.example.rxandroid.servecies.service.retrofit.withoutcache;


import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Album.model.Album;
import com.example.rxandroid.servecies.Api.AlbumsApi;
import com.example.rxandroid.servecies.service.retrofit.BaseRetrofit;

import java.util.List;

import mohamedibrahim.networklib.services.common.NetworkListener;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public class AlbumListService extends BaseRetrofit<List<Album>> {
    private final AlbumsApi api;
    private Subscription subscription;


    public AlbumListService(NetworkListener<List<Album>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(AlbumsApi.class);

    }

    @Override
    public Subscription getSubscription() {
        return subscription;
    }


    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        subscription = api.getAlbums().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Album>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                handleException(e);
            }

            @Override
            public void onNext(List<Album> alba) {
                if (networkListener == null) return;
                networkListener.onSuccess(alba);
            }
        });
    }


}
