package com.example.rxandroid.Main.presenter.listPresenter;


import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.common.connectivity.ConnectivityReceiverListener;
import com.example.rxandroid.common.connectivity.ConnectivityUtils;
import com.example.rxandroid.common.connectivity.IConnectivityWrapper;


import com.example.rxandroid.servecies.NetworkUtils;


import java.util.ArrayList;
import java.util.List;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

import static com.example.rxandroid.common.Keys.BUNDLE.LIST_POST_KEY;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public class MainPresenter implements ListPresenter, NetworkListener<List<Post>>, ConnectivityReceiverListener {


    private final MainInterface listener;
    private final BaseService service;
    private IConnectivityWrapper iConnectivityWrapper;

    public MainPresenter(MainInterface view) {
        listener = view;
        service = NetworkUtils.getForumListService(this);


    }

    @Override
    public void loadPosts(Bundle b) {
        if (b == null) service.execute(null);
        else onSuccess(b.<Post>getParcelableArrayList(LIST_POST_KEY));
    }

    @Override
    public void saveState(Bundle b, List<Post> posts) {
        if (b != null && posts != null && posts.size() > 0) {
            b.putParcelableArrayList(LIST_POST_KEY, (ArrayList<? extends Parcelable>) posts);
        }
    }


    @Override
    public void onSuccess(List<Post> posts) {
        if (listener != null && posts != null) listener.loadPosts(posts);
    }

    @Override
    public void onError(NetworkException e) {
        if (listener != null && e != null && !TextUtils.isEmpty(e.getMessage()))
            listener.onFail(e.getMessage());
    }


    public void stopService() {
        service.cancel();
    }


    @Override
    public void onAttach() {
        if (iConnectivityWrapper == null)
            iConnectivityWrapper = ConnectivityUtils.getConnectivityWrapper();

        iConnectivityWrapper.register(this);


    }

    @Override
    public void onDetach() {
        if (iConnectivityWrapper != null) {
            iConnectivityWrapper.unRegister();
            iConnectivityWrapper = null;
        }
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) loadPosts(null); // load news
        else if (listener != null) listener.showNoInternetConnection();
    }
}
