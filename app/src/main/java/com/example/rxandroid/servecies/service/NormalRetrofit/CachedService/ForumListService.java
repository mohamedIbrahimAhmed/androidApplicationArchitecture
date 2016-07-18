package com.example.rxandroid.servecies.service.NormalRetrofit.CachedService;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.cach.CacheUtils;
import com.example.rxandroid.servecies.Api.ForumApi;
import com.example.rxandroid.servecies.service.NormalRetrofit.BaseRetrofitCache;

import java.util.List;

import mohamedibrahim.networklib.services.common.NetworkListener;
import retrofit2.Call;

/**
 * Created by mohamed.ibrahim on 7/11/2016.
 */

public class ForumListService extends BaseRetrofitCache<List<Post>> {


    private final ForumApi api;

    private Call<List<Post>> requestCall;
    private final BaseCache<Post, String> baseCache;

    public ForumListService(NetworkListener<List<Post>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
        baseCache = CacheUtils.getPostCache();


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
    public List<Post> getCached() {
        try {
            return baseCache.query();
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public void CacheData(final List<Post> list) {
        try {
            baseCache.add(list);
        } catch (CacheException e) {
            Log.e("RX_TAG", e.getMessage());
        }
    }


}
