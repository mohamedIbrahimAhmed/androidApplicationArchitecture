package com.example.rxandroid.servecies.service.retrofit.CachedService;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.cach.CacheUtils;
import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.common.Keys;
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

public class CommentListService extends BaseRetrofitCache<List<Comment>> {

    private final ForumApi api;
    private Subscription subscription;
    private final BaseCache<Comment, String> baseCache;
    private String postId;


    public CommentListService(NetworkListener<List<Comment>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
        baseCache = CacheUtils.getCommentCache();
    }

    @Override
    protected Subscription getSubscription() {
        return subscription;
    }


    @Override
    public List<Comment> getCached() {
        try {
            return baseCache.queryAll(postId);
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public void CacheData(final List<Comment> list) {
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
    public void beforeExecution(ArrayMap<String, String> params) {
        this.postId = params.get(Keys.PARAMS.POST_ID);
    }


    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {


        subscription = api.getComments(postId).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getCachedOrThrowException(e);
                    }


                    @Override
                    public void onNext(List<Comment> comments) {
                        if (networkListener != null)
                            networkListener.onSuccess(comments);

                        if (isCached())
                            CacheData(comments);


                    }
                });

    }


}
