package com.example.rxandroid.comments.presenter;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.comments.model.Comment;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public interface CommentView {

    void onPostLoaded(Post post);

    void onCommentsLoaded(List<Comment> list);

    void onFail(String message);

}
