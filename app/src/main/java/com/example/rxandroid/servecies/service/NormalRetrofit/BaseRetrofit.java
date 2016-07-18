package com.example.rxandroid.servecies.service.NormalRetrofit;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.App;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;

import mohamedibrahim.networklib.services.common.NetworkListener;
import mohamedibrahim.networklib.services.manager.RetrofitManager;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class BaseRetrofit<T> extends mohamedibrahim.networklib.services.Retrofit.BaseRetrofit<T> {
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
}
