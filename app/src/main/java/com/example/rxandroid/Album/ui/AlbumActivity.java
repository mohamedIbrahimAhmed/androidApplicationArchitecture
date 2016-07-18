package com.example.rxandroid.Album.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rxandroid.Album.adapter.AlbumAdapter;
import com.example.rxandroid.Album.model.Album;
import com.example.rxandroid.Album.presenter.main.AlbumPresenter;
import com.example.rxandroid.Album.presenter.main.AlbumPresenterImpl;
import com.example.rxandroid.Album.presenter.main.AlbumView;
import com.example.rxandroid.R;

import java.util.List;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public class AlbumActivity extends AppCompatActivity implements AlbumView {
    private AlbumAdapter adapter;
    private AlbumPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiRecyclerView();

        presenter = new AlbumPresenterImpl(this);
        presenter.loadAlbums();
    }

    private void intiRecyclerView() {
        adapter = new AlbumAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        }


    }

    @Override
    public void onAlbumLoaded(List<Album> list) {
        adapter.addItems(list);
    }

    @Override
    public void onFail(String msg) {
        Log.d("Error", msg);
    }

    @Override
    protected void onDestroy() {
        presenter.stopService();
        super.onDestroy();
    }
}
