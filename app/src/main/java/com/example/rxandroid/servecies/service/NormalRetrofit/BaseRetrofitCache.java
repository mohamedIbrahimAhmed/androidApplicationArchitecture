package com.example.rxandroid.servecies.service.NormalRetrofit;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.rxandroid.App;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.Retrofit.RetrofitWithCache;
import mohamedibrahim.networklib.services.common.NetworkListener;
import mohamedibrahim.networklib.services.manager.RetrofitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class BaseRetrofitCache<T> extends RetrofitWithCache<T> implements Callback<T> {
    protected final RetrofitManager retrofitManager;

    protected BaseRetrofitCache(NetworkListener<T> networkListener) {
        super(networkListener);
        retrofitManager = App.getRetrofitManager();
        Log.e("SERVICE", "ForumListService normal Retrofit with cache");

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
    protected void getCachedOrThrowException(Throwable e) {

        if (networkListener == null) return;

        if (isCached() && getCached() != null)
            networkListener.onSuccess(getCached());
        else
            networkListener.onError(new NetworkException(e.getMessage()));

    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (networkListener != null)
            networkListener.onSuccess(response.body());

        if (isCached())
            CacheData(response.body());

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        getCachedOrThrowException(t);
    }
}

