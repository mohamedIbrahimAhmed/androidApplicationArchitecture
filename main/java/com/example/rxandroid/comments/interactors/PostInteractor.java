package com.example.rxandroid.comments.interactors;

import android.support.v4.util.ArrayMap;

import com.example.rxandroid.Main.model.Post;
import com.example.rxandroid.common.Keys;
import com.example.rxandroid.servecies.NetworkUtils;

import mohamedibrahim.networklib.services.common.BaseService;
import mohamedibrahim.networklib.services.common.NetworkListener;

/**
 * Created by mohamed.ibrahim on 7/12/2016.
 */

public class PostInteractor implements IPostInteractor {

    private BaseService postDetailService;

    @Override
    public void getPostDetails(int postId, NetworkListener<Post> postNetworkListener) {
        postDetailService = NetworkUtils.getPostDetailsService(postNetworkListener);
        final ArrayMap<String, String> map = new ArrayMap<>();
        map.put(Keys.PARAMS.POST_ID, String.valueOf(postId));
        postDetailService.execute(map);
    }

    @Override
    public void stopPostDetailsService() {
        if (postDetailService != null && !postDetailService.isCanceled()) {
            postDetailService.cancel();
        }
    }


}
