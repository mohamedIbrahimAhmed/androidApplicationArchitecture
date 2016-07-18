package com.example.rxandroid.servecies.service.AsyncHttp;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import mohamedibrahim.networklib.services.AsyncHttp.BaseAsyncHttp;
import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.NetworkListener;
import mohamedibrahim.networklib.services.manager.AsyncHttpManager;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public class ForumListService extends BaseAsyncHttp {


    private final NetworkListener<List<Post>> networkListener;
    private AsyncHttpClient client;

    public ForumListService(NetworkListener<List<Post>> networkListener) {
        this.networkListener = networkListener;


    }


    @Override
    public boolean isNetworkAvailable() {
        return ConnectivityUtils.isConnected();
    }

    @Override
    public void beforeExecution(ArrayMap<String, String> params) {

    }

    @Override
    protected void fetchOnline(ArrayMap<String, String> params) {
        client = AsyncHttpManager.getInstance().getClient();
        client.get(Keys.URL.POSTS, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (networkListener == null) return;

                Gson gson = new Gson();
                List<Post> posts = gson.fromJson(response.toString(), new TypeToken<List<Post>>() {
                }.getType());
                networkListener.onSuccess(posts);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (networkListener == null) return;
                networkListener.onError(new NetworkException(throwable.getMessage()));
            }
        });
    }

    @Override
    protected void handleException(Throwable e) {

    }


    @Override
    protected AsyncHttpClient getServiceRequest() {
        return client;
    }

    @Override
    protected String getServiceName() {
        return Keys.URL.POSTS;
    }


}
