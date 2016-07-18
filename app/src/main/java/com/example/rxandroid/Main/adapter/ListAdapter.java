package com.example.rxandroid.Main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxandroid.R;
import com.example.rxandroid.comments.ui.CommentsActivity;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.common.Keys;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    List<Post> itemsArrayList;
    Context context;

    public ListAdapter(Context context) {
        itemsArrayList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_post_item, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.textViewItemTitle.setText(itemsArrayList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemTitle;

        public ListViewHolder(View view) {
            super(view);
            textViewItemTitle = (TextView) view.findViewById(R.id.textViewItemTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommentsActivity.class);
                    intent.putExtra(Keys.BUNDLE.POST_ID, itemsArrayList.get(getAdapterPosition()).id);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void addItems(List<Post> postList) {
        itemsArrayList.addAll(postList);
        notifyDataSetChanged();
    }
}
