package com.example.rxandroid.servecies.service.NormalRetrofit.CachedService;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.cach.CacheUtils;
import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.servecies.Api.ForumApi;
import com.example.rxandroid.servecies.service.NormalRetrofit.BaseRetrofitCache;

import java.util.List;

import mohamedibrahim.networklib.services.common.NetworkListener;
import retrofit2.Call;

/**
 * Created by mohamed.ibrahim on 7/12/2016.
 */

public class CommentListService extends BaseRetrofitCache<List<Comment>> {


    private final ForumApi api;
    private Call<List<Comment>> requestCall;
    private final BaseCache<Comment, String> baseCache;
    private String postId;


    public CommentListService(NetworkListener<List<Comment>> networkListener) {
        super(networkListener);
        this.api = retrofitManager.create(ForumApi.class);
        baseCache = CacheUtils.getCommentCache();


    }

    @Override
    protected Call getRequestCall() {
        return requestCall;
    }

    @Override
    public void beforeExecution(ArrayMap<String, String> params) {
        this.postId = params.get(Keys.PARAMS.POST_ID);
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
    public void CacheData(List<Comment> list) {
        try {
            baseCache.add(list);
        } catch (CacheException e) {
            Log.e("RX_TAG", e.getMessage());
        }
    }

    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        requestCall = api._getComments(postId);
        requestCall.enqueue(this);
    }


}
