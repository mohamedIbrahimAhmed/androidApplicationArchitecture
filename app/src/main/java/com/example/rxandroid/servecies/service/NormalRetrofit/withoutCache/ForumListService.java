package com.example.rxandroid.servecies.service.NormalRetrofit.withoutCache;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.servecies.Api.ForumApi;
import com.example.rxandroid.servecies.service.NormalRetrofit.BaseRetrofit;

import java.util.List;

import mohamedibrahim.networklib.services.common.NetworkListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohamed.ibrahim on 7/11/2016.
 */

public class ForumListService extends BaseRetrofit<List<Post>> implements Callback<List<Post>> {

    private final ForumApi api;
    private Call<List<Post>> requestCall;

    public ForumListService(NetworkListener<List<Post>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
        Log.e("SERVICE", "ForumListService normal Retrofit");

    }

    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        requestCall = api._getPosts();
        requestCall.enqueue(this);
    }


    @Override
    protected Call getRequestCall() {
        return requestCall;
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (networkListener == null) return;
        networkListener.onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        handleException(t);
    }
}
