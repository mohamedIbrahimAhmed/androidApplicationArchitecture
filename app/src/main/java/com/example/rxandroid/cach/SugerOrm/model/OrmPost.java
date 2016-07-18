package com.example.rxandroid.cach.SugerOrm.model;

import com.example.rxandroid.Main.model.Post;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */
@Table
public class OrmPost extends SugarRecord {
    public OrmPost(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public void setId(Long id) {
        super.setId(this.id);
    }

    public OrmPost(Post post) {
        this(post.userId, post.id, post.title, post.body);
    }

    public OrmPost() {
    }

    public Post toPost() {
        return new Post(userId, (int) id, title, body);
    }

    public int userId;
    public long id;
    public String title;
    public String body;

}
