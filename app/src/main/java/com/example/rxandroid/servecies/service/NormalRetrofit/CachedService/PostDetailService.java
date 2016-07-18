package com.example.rxandroid.servecies.service.NormalRetrofit.CachedService;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.cachelib.DB.BaseCache;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.cach.CacheUtils;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.servecies.Api.ForumApi;
import com.example.rxandroid.servecies.service.NormalRetrofit.BaseRetrofitCache;

import mohamedibrahim.networklib.services.common.NetworkListener;
import retrofit2.Call;

/**
 * Created by mohamed.ibrahim on 7/12/2016.
 */

public class PostDetailService extends BaseRetrofitCache<Post> {

    private final ForumApi api;


    private final BaseCache<Post, String> baseCache;
    private String postId;
    private Call<Post> requestCall;

    public PostDetailService(NetworkListener<Post> networkListener) {
        super(networkListener);

        this.api = retrofitManager.create(ForumApi.class);
        baseCache = CacheUtils.getPostCache();
    }

    @Override
    public void beforeExecution(ArrayMap<String, String> params) {
        postId = params.get(Keys.PARAMS.POST_ID);
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
    public void CacheData(Post post) {
        try {
            baseCache.add(post);
        } catch (Throwable e) {
            Log.e("RX_TAG", e.getMessage());
        }
    }

    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        requestCall = api._getPost(postId);
        requestCall.enqueue(this);
    }

    @Override
    protected Call getRequestCall() {
        return requestCall;
    }


}
