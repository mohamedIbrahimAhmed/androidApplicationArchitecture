package com.example.rxandroid.servecies.service.retrofit.CachedService;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.cach.CacheUtils;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.servecies.Api.ForumApi;
import com.example.rxandroid.servecies.service.retrofit.BaseRetrofitCache;

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

public class PostDetailsService extends BaseRetrofitCache<Post> {

    private final ForumApi api;
    private Subscription subscription;
    private final BaseCache<Post, String> baseCache;
    private String postId;

    public PostDetailsService(NetworkListener<Post> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
        baseCache = CacheUtils.getPostCache();
    }

    @Override
    public Subscription getSubscription() {
        return subscription;
    }


    @Override
    public Post getCached() {
        try {
            return baseCache.query(postId);
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public void CacheData(final Post post) {
        Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    baseCache.add(post);
                } catch (CacheException e) {
                    Log.e("RX_TAG", e.getMessage());
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }


    @Override
    public void beforeExecution(ArrayMap<String, String> params) {
        postId = params.get(Keys.PARAMS.POST_ID);
    }


    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {

        subscription = api.getPost(postId).
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getCachedOrThrowException(e);
                    }

                    @Override
                    public void onNext(Post post) {
                        if (networkListener == null) return;
                        if (isCached()) CacheData(post);
                        networkListener.onSuccess(post);

                    }

                });

    }


}
