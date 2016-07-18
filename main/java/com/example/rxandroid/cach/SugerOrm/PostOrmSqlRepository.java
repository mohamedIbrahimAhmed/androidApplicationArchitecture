package com.example.rxandroid.cach.SugerOrm;

import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.Main.model.Post;

import com.example.rxandroid.cach.SugerOrm.model.OrmPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public class PostOrmSqlRepository implements BaseCache<Post, Integer> {
    @Override
    public void add(Post post) throws CacheException {
        OrmPost ormPost = new OrmPost(post);
        ormPost.save();
    }

    @Override
    public void add(List<Post> t) throws CacheException {
        if (t == null) throw new CacheException("List of post is null...");
        if (t.size() == 0) throw new CacheException("List of post is empty...");

        final int size = t.size();
        for (int i = 0; i < size; i++) add(t.get(i));

    }

    @Override
    public List<Post> query() throws CacheException {
        List<OrmPost> list = OrmPost.listAll(OrmPost.class);
        final List<Post> posts = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            posts.add(list.get(i).toPost());
        }

        return posts;

    }

    @Override
    public Post query(Integer s) throws CacheException {
        return OrmPost.findById(OrmPost.class, s).toPost();
    }

    @Override
    public List<Post> queryAll(Integer integer) throws CacheException {
        return null;
    }

    @Override
    public boolean remove(Post post) throws CacheException {
        return new OrmPost(post).delete();

    }

    @Override
    public void update(Post item) throws CacheException {
        new OrmPost(item).save();

    }
}
