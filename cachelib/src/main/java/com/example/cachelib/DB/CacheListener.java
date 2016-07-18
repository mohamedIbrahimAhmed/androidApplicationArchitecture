package com.example.cachelib.DB;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public interface CacheListener<T> {
    void onSuccess(T t);

    void onError(CacheException e);
}
