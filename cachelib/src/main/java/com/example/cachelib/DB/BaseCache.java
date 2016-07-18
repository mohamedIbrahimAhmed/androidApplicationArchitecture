package com.example.cachelib.DB;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public interface BaseCache<T, ID> {

    void add(T t) throws CacheException;

    void add(List<T> t) throws CacheException;

    List<T> query() throws CacheException;

    T query(ID id) throws CacheException;

    List<T> queryAll(ID id) throws CacheException;


    boolean remove(T t) throws CacheException;

    void update(T item) throws CacheException;

}
