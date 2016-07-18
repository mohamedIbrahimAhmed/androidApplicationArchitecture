package com.example.rxandroid.cach.SugerOrm.model;

import com.example.rxandroid.comments.model.Comment;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

@Table
public class OrmComment extends SugarRecord {


    public int postId;
    public long id;
    public String name;
    public String email;
    public String body;


    @Override
    public void setId(Long id) {
        super.setId(this.id);
    }

    public OrmComment(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public OrmComment(Comment c) {
        this(c.postId, c.id, c.name, c.email, c.body);
    }


    public Comment toComment() {
        return new Comment(postId, (int) id, name, email, body);
    }

}
