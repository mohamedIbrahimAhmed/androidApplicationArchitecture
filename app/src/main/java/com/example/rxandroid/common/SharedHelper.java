package com.example.rxandroid.common;

import com.example.rxandroid.App;

import static com.example.rxandroid.common.Keys.PREFS.GCM_TOKEN;


/**
 * Created by mohamed.ibrahim on 7/11/2016.
 */

public final class SharedHelper {


    public static void addToken(String token) {
        App.getPrefs().write(GCM_TOKEN, token);
    }
    public static String getToken() {
        return App.getPrefs().read(GCM_TOKEN, null);
    }


}
