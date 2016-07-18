package com.example.rxandroid.cach;

import com.example.cachelib.DB.BaseCache;
import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.cach.noSql.CommentNoSqlRepository;
import com.example.rxandroid.cach.noSql.PostNoSqlRepository;
import com.example.rxandroid.comments.model.Comment;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public final class CacheUtils {

    private CacheUtils() {
        /**
         * no constructor
         */
    }


    public static BaseCache<Post, String> getPostCache() {
        return new PostNoSqlRepository(); // no sql version
//        return new PostOrmSqlRepository(); // orm suger version

    }


    public static BaseCache<Comment, String> getCommentCache() {
//        return new CommentOrmSqlRepo(); // orm suger version
        return new CommentNoSqlRepository();
    }

}
