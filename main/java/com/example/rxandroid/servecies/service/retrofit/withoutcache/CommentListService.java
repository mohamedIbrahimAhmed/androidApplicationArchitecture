package com.example.rxandroid.servecies.service.retrofit.withoutcache;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.common.Keys;
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

public class CommentListService extends BaseRetrofit<List<Comment>> {

    private final ForumApi api;
    private Subscription subscription;


    public CommentListService(NetworkListener<List<Comment>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
    }

    @Override
    public Subscription getSubscription() {
        return subscription;
    }


    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        subscription = api.getComments(params.get(Keys.PARAMS.POST_ID)).
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        handleException(e);
                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        if (networkListener != null) networkListener.onSuccess(comments);

                    }
                });
    }


}
