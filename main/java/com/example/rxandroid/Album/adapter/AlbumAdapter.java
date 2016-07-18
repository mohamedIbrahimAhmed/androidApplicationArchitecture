package com.example.rxandroid.Album.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.rxandroid.Album.model.Album;
import com.example.rxandroid.Album.ui.AlbumDetailsActivity;
import com.example.rxandroid.R;
import com.example.rxandroid.common.Keys;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ListViewHolder> {

    private final List<Album> list;

    public AlbumAdapter() {
        this.list = new ArrayList<>();

    }


    public void addItems(List<Album> list) {
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_post_item, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewItemTitle;


        ListViewHolder(final View view) {
            super(view);
            textViewItemTitle = (TextView) view.findViewById(R.id.textViewItemTitle);


        }

        void bindView(final Album album) {
            textViewItemTitle.setText(album.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context context = v.getContext();
                    if (context != null) {
                        Intent intent = new Intent(v.getContext(), AlbumDetailsActivity.class);
                        intent.putExtra(Keys.BUNDLE.POST_ID, album.getId());
                        context.startActivity(intent);
                    }
                }
            });

        }
    }
}
