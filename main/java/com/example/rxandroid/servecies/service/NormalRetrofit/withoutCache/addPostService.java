package com.example.rxandroid.servecies.service.NormalRetrofit.withoutCache;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.servecies.Api.ForumApi;
import com.example.rxandroid.servecies.service.NormalRetrofit.BaseRetrofit;

import java.util.List;

import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohamed.ibrahim on 7/18/2016.
 */
public class addPostService extends BaseRetrofit<Post> {
    private final ForumApi api;
    private Call<Post> requestCall;

    public addPostService(NetworkListener<Post> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
    }


    @Override
    protected Call getRequestCall() {
        return requestCall;
    }

    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        final String body = params.get(Keys.PARAMS.POST_BODY);
        final String title = params.get(Keys.PARAMS.POST_TITLE);
        final int userId = Integer.valueOf(params.get(Keys.PARAMS.POST_USER_ID));
        requestCall = api._postPost(title, body, userId);
        requestCall.enqueue(this);
    }


}
