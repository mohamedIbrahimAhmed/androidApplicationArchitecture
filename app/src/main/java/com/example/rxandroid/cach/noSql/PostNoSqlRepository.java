package com.example.rxandroid.cach.noSql;

import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.Main.model.Post;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Book;
import io.paperdb.Paper;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public class PostNoSqlRepository implements BaseCache<Post, String> {


    private static final String Map_NAME = "_posts";
    private final Book book = Paper.book(Map_NAME);

    @Override
    public void add(Post post) throws CacheException {
        book.write(String.valueOf(post.id), post);

    }

    @Override
    public void add(List<Post> t) throws CacheException {
        if (t == null) throw new CacheException("List of post is null...");
        if (t.size() == 0) throw new CacheException("List of post is empty...");

        book.destroy(); // clear all previous data.
        final int size = t.size();
        for (int i = 0; i < size; i++) add(t.get(i));


    }

    @Override
    public List<Post> query() throws CacheException {

        final List<Post> list = new ArrayList<>();
        final List<String> allKeys = book.getAllKeys();
        final int size = allKeys.size();
        for (int i = 0; i < size; i++) list.add(book.<Post>read(allKeys.get(i)));
        return list;
    }

    @Override
    public Post query(String key) throws CacheException {
        return book.read(key);
    }

    @Override
    public List<Post> queryAll(String s) throws CacheException {
        return null;
    }

    @Override
    public boolean remove(Post post) throws CacheException {
        if (post == null) throw new CacheException("Post must be not null...");
        book.delete(String.valueOf(post.id));
        return true;
    }

    @Override
    public void update(Post post) throws CacheException {
        if (post == null) throw new CacheException("Post must be not null...");
        add(post);
    }
}
