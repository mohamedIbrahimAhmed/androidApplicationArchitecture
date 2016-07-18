package com.example.rxandroid.servecies.service.retrofit.CachedService;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.cach.CacheUtils;
import com.example.rxandroid.servecies.Api.ForumApi;
import com.example.rxandroid.servecies.service.retrofit.BaseRetrofitCache;

import java.util.List;

import mohamedibrahim.networklib.services.common.NetworkListener;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public class ForumListServiceWithCache extends BaseRetrofitCache<List<Post>> {


    private final ForumApi api;
    private Subscription subscription;
    private final BaseCache<Post, String> baseCache;

    public ForumListServiceWithCache(NetworkListener<List<Post>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
        baseCache = CacheUtils.getPostCache();

    }


    @Override
    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public List<Post> getCached() {
        try {
            return baseCache.query();
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public void CacheData(final List<Post> list) {
        Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    baseCache.add(list);
                } catch (CacheException e) {
                    Log.e("RX_TAG", e.getMessage());
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
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
                getCachedOrThrowException(e);
            }

            @Override
            public void onNext(List<Post> posts) {
                if (networkListener == null) return;

                if (isCached()) CacheData(posts);
                networkListener.onSuccess(posts);


            }
        });
    }


}
