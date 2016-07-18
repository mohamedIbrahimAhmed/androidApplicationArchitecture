package com.example.rxandroid.servecies.service.retrofit;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.App;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.RxRetrofit.RetrofitServiceWithCache;
import mohamedibrahim.networklib.services.common.NetworkListener;
import mohamedibrahim.networklib.services.manager.RetrofitManager;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class BaseRetrofitCache<T> extends RetrofitServiceWithCache<T> {
    protected final RetrofitManager retrofitManager;

    protected BaseRetrofitCache(NetworkListener<T> networkListener) {
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
    protected void getCachedOrThrowException(Throwable e) {

        if (networkListener == null) return;

        if (isCached() && getCached() != null)
            networkListener.onSuccess(getCached());
        else
            networkListener.onError(new NetworkException(e.getMessage()));

    }
}
