package com.example.rxandroid;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.cachelib.Prefs.Prefs;
import com.example.rxandroid.common.Keys;
import com.orm.SugarApp;

import io.paperdb.Paper;
import mohamedibrahim.networklib.services.manager.RetrofitManager;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public class App extends SugarApp {
    private static App context;
    private static RetrofitManager retrofitManager;
    private static Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Paper.init(this);
        prefs = Prefs.with(this);


    }


    public static Prefs getPrefs() {
        return prefs;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return context;
    }


    public static RetrofitManager getRetrofitManager() {
        if (retrofitManager == null)
            retrofitManager = RetrofitManager.init(Keys.URL.FORUM_SERVER, false, null);
        return retrofitManager;
    }


}



