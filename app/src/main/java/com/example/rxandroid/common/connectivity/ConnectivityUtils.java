package com.example.rxandroid.common.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.rxandroid.App;
import com.example.rxandroid.common.connectivity.ReactiveNetwork.ReactiveNetworkWrapper;

/**
 * Created by mohamed.ibrahim on 7/10/2016.
 */

public final class ConnectivityUtils {

    private ConnectivityUtils() {
        /**
         * no constructor
         */
    }


    public static boolean isConnected() {

        try {
            ConnectivityManager
                    cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Throwable e) {
            return false;
        }
    }


    public static IConnectivityWrapper getConnectivityWrapper() {
//        return new ConnectivityReceiver(); // broadcast
        return new ReactiveNetworkWrapper(); // reactive network
    }

}
