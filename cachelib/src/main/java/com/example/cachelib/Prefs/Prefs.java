package com.example.cachelib.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by mohamed.ibrahim on 7/17/2016.
 */

public class Prefs {
    private static SharedPreferences sharedPreferences;
    private static Prefs prefsInstance;

    protected static final String PrefsName = "Preferences";

    protected interface DEFAULT_VALUE {
        String STRING = "";
        int INT = -1;
        double DOUBLE = -1d;
        float FLOAT = -1f;
        long LONG = -1L;
        boolean BOOLEAN = false;
    }

    private Prefs(@Nullable Context context) {
        if (context == null) throw new NullPointerException("context is null");
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PrefsName, Context.MODE_PRIVATE);
    }


    public static Prefs with(@Nullable Context context) {
        if (prefsInstance == null) prefsInstance = new Prefs(context);
        return prefsInstance;
    }


    /**
     * String helpers
     */
    public String read(String key) {
        return sharedPreferences.getString(key, DEFAULT_VALUE.STRING);
    }

    public String read(String key, String defaultVal) {
        return sharedPreferences.getString(key, defaultVal);
    }

    public void write(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }


    /**
     * int helpers
     */


    public int readInt(String key) {
        return sharedPreferences.getInt(key, DEFAULT_VALUE.INT);
    }

    public int readInt(String key, int defaultVal) {
        return sharedPreferences.getInt(key, defaultVal);
    }

    public void writeInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }


    /**
     * boolean helpers
     */

    public boolean readBoolean(String key) {
        return sharedPreferences.getBoolean(key, DEFAULT_VALUE.BOOLEAN);
    }

    public boolean readBoolean(String key, boolean defaultVal) {
        return sharedPreferences.getBoolean(key, defaultVal);
    }

    public void writeBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }


}
