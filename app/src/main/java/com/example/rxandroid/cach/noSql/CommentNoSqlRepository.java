package com.example.rxandroid.cach.noSql;

import android.util.Log;


import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.comments.model.Comment;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Book;
import io.paperdb.Paper;

/**
 * Created by mohamed.ibrahim on 7/10/2016.
 */

public class CommentNoSqlRepository implements BaseCache<Comment, String> {
    private static final String Map_NAME = "_comments";
    private final Book book = Paper.book(Map_NAME);

    @Override
    public void add(Comment comment) throws CacheException {
        book.write(String.valueOf(comment.id), comment);
        Log.e(Map_NAME, comment.id + " saved.");
    }

    @Override
    public void add(List<Comment> t) throws CacheException {
        if (t == null) throw new CacheException("List of comments is null...");
        if (t.size() == 0) throw new CacheException("List of comments is empty...");

        book.destroy(); // clear all previous data.
        final int size = t.size();
        for (int i = 0; i < size; i++) add(t.get(i));
    }


    @Override
    public List<Comment> query() throws CacheException {
        final List<Comment> list = new ArrayList<>();
        final List<String> allKeys = book.getAllKeys();
        final int size = allKeys.size();
        for (int i = 0; i < size; i++) list.add(book.<Comment>read(allKeys.get(i)));
        return list;
    }

    @Override
    public Comment query(String key) throws CacheException {
        return book.read(key);

    }

    @Override
    public List<Comment> queryAll(String key) throws CacheException {
        // return list of comment with Post id
        final List<Comment> list = new ArrayList<>();
        final List<String> allKeys = book.getAllKeys();
        final int postId = Integer.valueOf(key);
        final int size = allKeys.size();
        for (int i = 0; i < size; i++) {
            Comment comment = book.read(allKeys.get(i));
            if (comment.postId == postId) list.add(comment);
        }
        return list;
    }

    @Override
    public boolean remove(Comment comment) throws CacheException {
        if (comment == null) throw new CacheException("Comment must be not null...");
        book.delete(String.valueOf(comment.id));
        return true;
    }

    @Override
    public void update(Comment item) throws CacheException {
        if (item == null) throw new CacheException("Comment must be not null...");
        add(item);
    }
}
