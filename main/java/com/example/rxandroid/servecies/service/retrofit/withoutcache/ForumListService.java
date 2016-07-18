package com.example.rxandroid.servecies.service.retrofit.withoutcache;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.servecies.Api.ForumApi;
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

public class ForumListService extends BaseRetrofit<List<Post>> {

    private final ForumApi api;
    private Subscription subscription;


    public ForumListService(NetworkListener<List<Post>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);

    }


    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        subscription = api.getPosts().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Post>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                handleException(e);
            }

            @Override
            public void onNext(List<Post> posts) {
                if (networkListener != null) networkListener.onSuccess(posts);
            }
        });
    }

    @Override
    public Subscription getSubscription() {
        return subscription;
    }
}
