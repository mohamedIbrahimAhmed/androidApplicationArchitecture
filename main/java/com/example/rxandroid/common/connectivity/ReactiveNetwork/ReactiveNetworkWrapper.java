package com.example.rxandroid.common.connectivity.ReactiveNetwork;

import android.util.Log;

import com.example.rxandroid.common.connectivity.ConnectivityReceiverListener;
import com.example.rxandroid.common.connectivity.IConnectivityWrapper;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mohamed.ibrahim on 7/10/2016.
 */

public class ReactiveNetworkWrapper implements IConnectivityWrapper {
    private static final String APP_TAG = "ConnectivityReceiver";
    private ReactiveNetwork reactiveNetwork;
    private ConnectivityReceiverListener listener;
    private Subscription subscription;

    @Override
    public void register(ConnectivityReceiverListener listener) {

        this.listener = listener;

        reactiveNetwork = new ReactiveNetwork();
        subscription = reactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isConnectedToInternet) {
                        // do something with isConnectedToInternet value
                        Log.e(APP_TAG, "onReceive : is Connected : " + isConnectedToInternet);
                        onNetworkStateChanged(isConnectedToInternet);
                    }
                });
    }

    @Override
    public void unRegister() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        subscription = null;
        reactiveNetwork = null;
        listener = null;

    }

    @Override
    public void onNetworkStateChanged(boolean isConnected) {
        if (listener != null)
            listener.onNetworkConnectionChanged(isConnected);
    }
}
