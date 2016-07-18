package com.example.rxandroid.servecies.service.Volley;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.rxandroid.App;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.NetworkListener;
import mohamedibrahim.networklib.services.manager.VolleyManager;
import mohamedibrahim.networklib.services.volley.VolleyBaseService;


/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public class ForumListService extends VolleyBaseService {


    private final NetworkListener<List<Post>> networkListener;
    private JsonArrayRequest request;

    public ForumListService(NetworkListener<List<Post>> networkListener) {
        this.networkListener = networkListener;


    }

    @Override
    public void beforeExecution(ArrayMap<String, String> params) {

    }

    @Override
    public void execute(ArrayMap<String, String> params) {
        if (isNetworkAvailable()) {
            fetchOnline(params);
        }


    }

    protected void fetchOnline(ArrayMap<String, String> params) {
        request = new JsonArrayRequest(Keys.URL.POSTS, new Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (networkListener == null) return;

                Gson gson = new Gson();
                List<Post> posts = gson.fromJson(response.toString(), new TypeToken<List<Post>>() {
                }.getType());
                networkListener.onSuccess(posts);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleException(error);


            }
        });

        VolleyManager.getInstance(App.getContext()).getmRequestQueue().add(request);
    }

    @Override
    protected void handleException(Throwable e) {
        if (networkListener == null) return;
        if (e == null) return;
        if (TextUtils.isEmpty(e.getMessage())) return;

        networkListener.onError(new NetworkException(e.getMessage()));
    }


    @Override
    public boolean isNetworkAvailable() {
        return ConnectivityUtils.isConnected();
    }

    @Override
    protected Request getServiceRequest() {
        return request;
    }
}
