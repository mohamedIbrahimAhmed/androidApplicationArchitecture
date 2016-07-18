package com.example.rxandroid.Album.presenter.details;


import com.example.rxandroid.Album.model.Album;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public interface DetailsView {
    void onAlbumLoaded(Album album);

    void onFail(String msg);


}
