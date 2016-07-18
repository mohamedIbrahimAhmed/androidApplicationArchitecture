package com.example.rxandroid.Main.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rxandroid.Main.adapter.ListAdapter;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.Main.presenter.ListPresenter;
import com.example.rxandroid.Main.presenter.MainInterface;
import com.example.rxandroid.Main.presenter.MainPresenter;
import com.example.rxandroid.Post.ui.AddPostActivity;
import com.example.rxandroid.R;
import com.example.rxandroid.databinding.ActivityMainBinding;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public class MainActivity extends FragmentActivity implements MainInterface, View.OnClickListener {


    private ActivityMainBinding binding;

    private ListAdapter listAdapter;
    private ListPresenter presenter;
    private List<Post> listOfPosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        intiRecyclerView();
        presenter = new MainPresenter(this);
        presenter.loadPosts(savedInstanceState);


        binding.btnAddPost.setOnClickListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter != null) presenter.saveState(outState, listOfPosts);
    }


    @Override
    protected void onPause() {
        presenter.onDetach();
        super.onPause();
    }

    private void intiRecyclerView() {
        listAdapter = new ListAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(listAdapter);


    }

    @Override
    protected void onDestroy() {
        clear();
        super.onDestroy();
    }

    private void clear() {
        if (presenter != null) presenter.stopService();
        presenter = null;

    }

    @Override
    public void loadPosts(List<Post> posts) {
        showView(true);

        listOfPosts = posts;
        listAdapter.addItems(listOfPosts);
    }

    private void showView(boolean isConnected) {
        binding.recyclerView.setVisibility(isConnected ? View.VISIBLE : View.GONE);
        binding.noConnection.setVisibility(!isConnected ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onFail(String msg) {
        Log.d("Error", msg);
    }

    @Override
    public void showNoInternetConnection() {
        showView(false);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnAddPost) {
            startActivity(new Intent(this, AddPostActivity.class));
        }
    }
}
