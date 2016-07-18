package com.example.rxandroid.Album.presenter.main;

import com.example.rxandroid.Album.model.Album;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public interface AlbumView {

    void onAlbumLoaded(List<Album> list);

    void onFail(String message);
}
