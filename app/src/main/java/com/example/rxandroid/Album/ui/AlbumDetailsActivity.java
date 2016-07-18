package com.example.rxandroid.Album.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rxandroid.Album.model.Album;
import com.example.rxandroid.Album.presenter.details.DetailPresenterImpl;
import com.example.rxandroid.Album.presenter.details.DetailsView;
import com.example.rxandroid.common.Keys;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public class AlbumDetailsActivity extends AppCompatActivity implements DetailsView {

    private TextView textView;
    private DetailPresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);


        final int id = getIntent().getExtras().getInt(Keys.BUNDLE.POST_ID, 1);
        presenter = new DetailPresenterImpl(this);
        presenter.loadAlbumDetail(id);

    }

    @Override
    public void onAlbumLoaded(Album album) {
        textView.setText(album.toString());
    }

    @Override
    public void onFail(String msg) {
        textView.setText(msg);
    }


    @Override
    protected void onDestroy() {
        presenter.stopService();
        super.onDestroy();
    }
}
