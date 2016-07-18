package mohamedibrahim.networklib.services.manager;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public final class AsyncHttpManager {
    private static AsyncHttpManager instance;
    private AsyncHttpClient client;

    private AsyncHttpManager() {
        client = new AsyncHttpClient();

    }


    public static AsyncHttpManager getInstance() {
        if (instance == null) {
            synchronized (AsyncHttpManager.class) {
                if (instance == null) {
                    instance = new AsyncHttpManager();

                }
            }
        }
        return instance ;
    }


    public AsyncHttpClient getClient() {
        return client;
    }
}
