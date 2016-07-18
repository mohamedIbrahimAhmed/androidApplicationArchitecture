package com.example.rxandroid.comments.adapter;

import android.content.Intent;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxandroid.Album.ui.AlbumActivity;
import com.example.rxandroid.R;
import com.example.rxandroid.comments.model.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private final List<Comment> commentsList;

    public CommentsAdapter() {
        commentsList = new ArrayList<>();
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        holder.bindData(commentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textViewCommentName, textViewCommentEmail, textViewCommentBody;

        CommentsViewHolder(View view) {
            super(view);
            textViewCommentBody = (TextView) view.findViewById(R.id.textViewCommentBody);
            textViewCommentEmail = (TextView) view.findViewById(R.id.textViewCommentEmail);
            textViewCommentName = (TextView) view.findViewById(R.id.textViewCommentName);
            view.setOnClickListener(this);
        }

        void bindData(Comment comment) {
            textViewCommentName.setText(comment.name);
            textViewCommentEmail.setText(comment.email);
            textViewCommentBody.setText(comment.body);

            final int bgColor = comment.getBackgroundColor();
            textViewCommentName.setTextColor(bgColor);
            textViewCommentEmail.setTextColor(bgColor);
            textViewCommentBody.setTextColor(bgColor);

        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), AlbumActivity.class));
        }
    }

    public void addItems(List<Comment> commentList) {
        commentsList.addAll(commentList);
        notifyDataSetChanged();
    }
}
