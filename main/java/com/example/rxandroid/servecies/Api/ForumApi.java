package com.example.rxandroid.servecies.Api;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.comments.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public interface ForumApi {


    //** Api without Observable

    @GET("/posts")
    Call<List<Post>> _getPosts();

    @GET("/posts/{id}")
    Call<Post> _getPost(@Path("id") String postId);

    @GET("/comments")
    Call<List<Comment>> _getComments(@Query("postId") String postId);

    @POST("posts")
    Call<Post> _postPost(@Body Post post);


    @FormUrlEncoded
    @POST("/posts")
    Call<Post> _postPost(@Field("title") String title, @Field("body") String body, @Field("userId") int userId);


    //** Api with Observable

    @GET("/posts")
    Observable<List<Post>> getPosts();

    @GET("/posts/{id}")
    Observable<Post> getPost(@Path("id") String postId);

    @GET("/comments")
    Observable<List<Comment>> getComments(@Query("postId") String postId);

    @POST("/posts")
    Observable<Post> postPost(Post post);
}


