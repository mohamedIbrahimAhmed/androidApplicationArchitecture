package com.example.rxandroid.common;

/**
 * Created by mohamed.ibrahim on 7/18/2016.
 */

public interface Keys {
    interface BUNDLE {
        String POST_ID = "postId";
        String LIST_POST_KEY = "list_post_key";
    }

    interface PARAMS {
        String POST_ID = "post_id";

        String POST_TITLE = "title";
        String POST_BODY = "body";
        String POST_USER_ID = "userId";

    }

    interface PREFS {
        String GCM_TOKEN = "gcm_token";
    }

    interface URL {
        String FORUM_SERVER = "http://jsonplaceholder.typicode.com";
        String POSTS = FORUM_SERVER + "/posts";
    }
}
