package com.example.rxandroid.servecies;

import com.example.rxandroid.Album.model.Album;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.servecies.service.NormalRetrofit.CachedService.ForumListService;
import com.example.rxandroid.servecies.service.NormalRetrofit.CachedService.PostDetailService;
import com.example.rxandroid.servecies.service.NormalRetrofit.withoutCache.addPostService;
import com.example.rxandroid.servecies.service.retrofit.withoutcache.AlbumListService;
import com.example.rxandroid.servecies.service.retrofit.withoutcache.AlbumService;

import java.util.List;

import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public final class NetworkUtils {

    private NetworkUtils() {
        /**
         no constructor
         */
    }


    public static BaseService getForumListService(NetworkListener<List<Post>> params) {
//        return new ForumListServiceWithCache(params); // version with cache
//        return new ForumListService(params); // version without cache
        return new ForumListService(params); // normal version without cache
//        return new ForumListService(params); // normal version with cache

    }


    public static BaseService getPostDetailsService(NetworkListener<Post> params) {
//        return new PostDetailsService(params); // version without cache
//        return new PostDetailsService(params); // version with cache

        return new PostDetailService(params); // normal retrofit with cache
    }

    public static BaseService CommentListService(NetworkListener<List<Comment>> params) {
//        return new CommentListService(params); // version without cache
//        return new com.example.rxandroid.servecies.service.retrofit.CachedService.CommentListService(params); // version with cache

        return new com.example.rxandroid.servecies.service.NormalRetrofit.CachedService.CommentListService(params);
    }

    public static BaseService getAlbumService(NetworkListener<Album> params) {
        return new AlbumService(params);
    }


    public static BaseService getAlbumListService(NetworkListener<List<Album>> params) {
        return new AlbumListService(params);
    }

    public static BaseService getAddPostService(NetworkListener<Post> params) {
        return new addPostService(params);
    }
}
