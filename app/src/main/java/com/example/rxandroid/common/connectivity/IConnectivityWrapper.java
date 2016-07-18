package com.example.rxandroid.common.connectivity;

/**
 * Created by mohamed.ibrahim on 7/10/2016.
 */

public interface IConnectivityWrapper {


    void register(ConnectivityReceiverListener listener);

    void unRegister();

    void onNetworkStateChanged(boolean isConnected);
}
