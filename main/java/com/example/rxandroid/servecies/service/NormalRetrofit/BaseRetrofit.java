package com.example.rxandroid.servecies.service.NormalRetrofit;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.App;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;

import mohamedibrahim.networklib.services.common.NetworkListener;
import mohamedibrahim.networklib.services.manager.RetrofitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class BaseRetrofit<T> extends mohamedibrahim.networklib.services.Retrofit.BaseRetrofit<T> implements Callback<T> {
    protected final RetrofitManager retrofitManager;

    protected BaseRetrofit(NetworkListener<T> networkListener) {
        super(networkListener);
        retrofitManager = App.getRetrofitManager();

    }


    @Override
    public void beforeExecution(ArrayMap<String, String> params) {
        //** do no thing
    }


    @Override
    public boolean isNetworkAvailable() {
        return ConnectivityUtils.isConnected();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (networkListener == null) return;
        networkListener.onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        handleException(t);
    }
}
