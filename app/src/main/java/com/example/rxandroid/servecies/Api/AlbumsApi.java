package com.example.rxandroid.servecies.Api;

import com.example.rxandroid.Album.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public interface AlbumsApi {


    //** Api without Observable

    @GET("/albums")
    Call<List<Album>> _getAlbums();


    @GET("/albums/{id}")
    Call<Album> _getAlbum(@Path("id") String albumId);

    //** Api with Observable

    @GET("/albums")
    Observable<List<Album>> getAlbums();


    @GET("/albums/{id}")
    Observable<Album> getAlbum(@Path("id") String albumId);
}
