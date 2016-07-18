package com.example.rxandroid.cach.SugerOrm;


import com.example.cachelib.DB.BaseCache;
import com.example.cachelib.DB.CacheException;
import com.example.rxandroid.cach.SugerOrm.model.OrmComment;
import com.example.rxandroid.comments.model.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public class CommentOrmSqlRepo implements BaseCache<Comment, String> {
    @Override
    public void add(Comment comment) throws CacheException {

        OrmComment orm = new OrmComment(comment);
        orm.save();

    }

    @Override
    public void add(List<Comment> t) throws CacheException {
        if (t == null) throw new CacheException("List of comments is null...");
        if (t.size() == 0) throw new CacheException("List of comments is empty...");

        final int size = t.size();
        for (int i = 0; i < size; i++) add(t.get(i));
    }

    @Override
    public List<Comment> query() throws CacheException {
        List<OrmComment> list = OrmComment.listAll(OrmComment.class);
        final List<Comment> comments = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            comments.add(list.get(i).toComment());
        }

        return comments;
    }

    @Override
    public Comment query(String postId) throws CacheException {
        List<OrmComment> ormComments = OrmComment.find(OrmComment.class, "post_id = ? ", postId);
        if (ormComments != null && ormComments.size() > 0) {
            return ormComments.get(0).toComment();
        }
        return new Comment();
    }

    @Override
    public List<Comment> queryAll(String postId) throws CacheException {
        final List<Comment> list = new ArrayList<>();
        List<OrmComment> ormComments = OrmComment.find(OrmComment.class, " post_id = ? ", postId);
        if (ormComments != null && ormComments.size() > 0) {
            final int size = ormComments.size();
            for (int i = 0; i < size; i++) {
                list.add(ormComments.get(i).toComment());
            }
        }


        return list;
    }

    @Override
    public boolean remove(Comment comment) throws CacheException {
        return new OrmComment(comment).delete();

    }

    @Override
    public void update(Comment item) throws CacheException {
        new OrmComment(item).save();
    }
}
