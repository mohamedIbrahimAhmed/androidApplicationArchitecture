package com.example.cachelib.DB;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public class CacheException extends Exception {

    private int resId;
    private String message;

    public CacheException(String message) {
        this.message = message;
    }

    public CacheException(int messageResId) {
        this.resId = messageResId;
    }


    public int getMessageResId() {
        return resId;
    }

    public String getMessage() {
        return message;
    }

}
