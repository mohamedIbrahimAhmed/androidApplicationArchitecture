package com.example.rxandroid.comments.presenter;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.comments.interactors.CommentInteractor;
import com.example.rxandroid.comments.interactors.ICommentInteractor;
import com.example.rxandroid.comments.interactors.IPostInteractor;
import com.example.rxandroid.comments.interactors.PostInteractor;

import java.util.List;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public class CommentsPresenter implements DetailPresenter {

    private final CommentView listener;
    private final ICommentInteractor commentInteractor;
    private final IPostInteractor postInteractor;


    public CommentsPresenter(CommentView commentView) {
        listener = commentView;
        commentInteractor = new CommentInteractor();
        postInteractor = new PostInteractor();
    }


    @Override
    public void load(int postID) {
        if (isInvalidPostID(postID)) {
            if (listener != null) listener.onFail("in valid Post");
            return;
        }

        postInteractor.getPostDetails(postID, new NetworkListener<Post>() {
            @Override
            public void onSuccess(Post post) {
                if (listener != null) listener.onPostLoaded(post);
            }

            @Override
            public void onError(NetworkException e) {
                if (listener != null) listener.onFail(e.getMessage());

            }
        });

        commentInteractor.getCommentList(postID, new NetworkListener<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> comments) {
                if (listener != null) listener.onCommentsLoaded(comments);
            }

            @Override
            public void onError(NetworkException e) {
                if (listener != null) listener.onFail(e.getMessage());
            }
        });


    }

    private boolean isInvalidPostID(int postID) {
        return postID <= 0;
    }


    @Override
    public void stopServices() {
        postInteractor.stopPostDetailsService();
        commentInteractor.stopCommentListService();


    }


}
