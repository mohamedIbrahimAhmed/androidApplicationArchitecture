package com.example.rxandroid.servecies.service.retrofit.withoutcache;


import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Album.model.Album;
import com.example.rxandroid.servecies.Api.AlbumsApi;
import com.example.rxandroid.servecies.service.retrofit.BaseRetrofit;

import mohamedibrahim.networklib.services.common.NetworkListener;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public class AlbumService extends BaseRetrofit<Album> {

    public interface PARAMS {
        String ALBUM_ID = "AlbumId";
    }


    private final AlbumsApi api;
    private Subscription subscription;


    public AlbumService(NetworkListener<Album> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(AlbumsApi.class);

    }


    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        subscription = api.getAlbum(params.get(PARAMS.ALBUM_ID)).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Album>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                handleException(e);
            }

            @Override
            public void onNext(Album a) {
                if (networkListener != null) networkListener.onSuccess(a);

            }
        });
    }


    @Override
    public Subscription getSubscription() {
        return subscription;
    }
}
