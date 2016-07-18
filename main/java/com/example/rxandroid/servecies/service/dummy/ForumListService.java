package com.example.rxandroid.servecies.service.dummy;

import android.os.AsyncTask;
import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;


import java.util.ArrayList;
import java.util.List;

import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public class ForumListService implements BaseService {

    private final NetworkListener<List<Post>> networkListener;

    public ForumListService(NetworkListener<List<Post>> networkListener) {
        this.networkListener = networkListener;

    }

    @Override
    public void beforeExecution(ArrayMap<String, String> params) {

    }

    @Override
    public void execute(ArrayMap<String, String> params) {
        new AsyncData(networkListener).execute();
    }


    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void cancel() {

    }


    @Override
    public boolean isNetworkAvailable() {
        return ConnectivityUtils.isConnected();
    }

    private static class AsyncData extends AsyncTask<Void, Void, Void> {
        private final NetworkListener<List<Post>> networkListener;
        private final List<Post> posts = new ArrayList<>();

        AsyncData(NetworkListener<List<Post>> networkListener) {
            this.networkListener = networkListener;
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                posts.add(new Post(i, i, " title " + i, "body " + i));
            }

            return null;

        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (networkListener != null) {
                networkListener.onSuccess(posts);
            }
        }
    }
}
