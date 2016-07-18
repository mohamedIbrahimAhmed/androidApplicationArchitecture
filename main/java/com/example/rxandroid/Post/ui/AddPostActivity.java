package com.example.rxandroid.Post.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rxandroid.Post.presenter.AddPostPresenter;
import com.example.rxandroid.Post.presenter.PostPresenterImpl;
import com.example.rxandroid.R;
import com.example.rxandroid.databinding.AddPostActivityBinding;

/**
 * Created by mohamed.ibrahim on 7/18/2016.
 */

public class AddPostActivity extends AppCompatActivity implements PostView, View.OnClickListener {


    private AddPostActivityBinding vDB;
    private AddPostPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vDB = DataBindingUtil.setContentView(this, R.layout.add_post_activity);
        presenter = new PostPresenterImpl(this);

        vDB.btnSubmit.setOnClickListener(this);


    }

    @Override
    public void showProgress() {
        vDB.pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vDB.pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void disablePostViews() {
        vDB.edUserId.setEnabled(false);
        vDB.edPostTitle.setEnabled(false);
        vDB.edPostBody.setEnabled(false);
    }

    @Override
    public void enablePostViews() {
        vDB.edUserId.setEnabled(true);
        vDB.edPostTitle.setEnabled(true);
        vDB.edPostBody.setEnabled(true);
    }

    @Override
    public void onTitleViewError(String message) {
        vDB.edPostTitle.setError(message);
    }

    @Override
    public void onBodyViewError(String message) {
        vDB.edPostBody.setError(message);
    }

    @Override
    public void onUserIdViewError(String message) {
        vDB.edUserId.setError(message);
    }

    @Override
    public void onPostSubmittedSuccessfully(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("POST", message);
    }

    @Override
    public void clearPostView() {
        vDB.edUserId.setText("");
        vDB.edPostTitle.setText("");
        vDB.edPostBody.setText("");
        vDB.edPostTitle.requestFocus();
    }

    @Override
    public void onPostSubmittedFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("POST", message);
    }

    @Override
    public void onClick(View v) {
        if (null != v && v == vDB.btnSubmit && presenter != null) {

            final String userId = vDB.edUserId.getText().toString();
            final String title = vDB.edPostTitle.getText().toString();
            final String body = vDB.edPostBody.getText().toString();
            presenter.addPost(userId, title, body);
        }
    }
}
