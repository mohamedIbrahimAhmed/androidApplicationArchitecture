package com.example.rxandroid.common.connectivity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.rxandroid.common.connectivity.ConnectivityReceiverListener;
import com.example.rxandroid.common.connectivity.IConnectivityWrapper;

/**
 * Created by mohamed.ibrahim on 7/10/2016.
 */

public class ConnectivityReceiver extends BroadcastReceiver implements IConnectivityWrapper {
    private static final String APP_TAG = "ConnectivityReceiver";
    private static ConnectivityReceiverListener listener;


    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        Log.e(APP_TAG, "onReceive : is Connected : " + isConnected);
        onNetworkStateChanged(isConnected);


    }


    @Override
    public void register(ConnectivityReceiverListener l) {
        listener = l;
    }

    @Override
    public void unRegister() {
        listener = null;
    }

    @Override
    public void onNetworkStateChanged(boolean isConnected) {
        if (listener != null)
            listener.onNetworkConnectionChanged(isConnected);
    }
}
