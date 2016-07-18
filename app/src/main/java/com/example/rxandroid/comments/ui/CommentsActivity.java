package com.example.rxandroid.comments.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.R;
import com.example.rxandroid.comments.adapter.CommentsAdapter;
import com.example.rxandroid.comments.model.Comment;
import com.example.rxandroid.comments.presenter.CommentView;
import com.example.rxandroid.comments.presenter.CommentsPresenter;
import com.example.rxandroid.comments.presenter.DetailPresenter;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.databinding.ActivityCommentsBinding;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public class CommentsActivity extends FragmentActivity implements CommentView {

    private ActivityCommentsBinding binding;
    private CommentsAdapter commentsAdapter;
    private DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments);
        initViews();
        presenter = new CommentsPresenter(this);
        presenter.load(getIntent().getIntExtra(Keys.BUNDLE.POST_ID, 0));


    }

    private void initViews() {
        commentsAdapter = new CommentsAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentsAdapter);


    }


    @Override
    public void onPostLoaded(Post post) {
        binding.textViewTitle.setText(post.title);
        binding.textViewBody.setText(post.body);
    }

    @Override
    protected void onDestroy() {
        presenter.stopServices();
        super.onDestroy();
    }

    @Override
    public void onCommentsLoaded(List<Comment> comments) {
        commentsAdapter.addItems(comments);
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
