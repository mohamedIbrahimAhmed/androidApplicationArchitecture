package mohamedibrahim.networklib.services.manager;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public final class VolleyManager {
    private static VolleyManager instance;
    private RequestQueue mRequestQueue;


    private VolleyManager(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

    }

    public static VolleyManager getInstance(Context context) {
        if (instance == null) {
            synchronized (VolleyManager.class) {
                if (instance == null) {
                    instance = new VolleyManager(context);
                }
            }
        }
        return instance;
    }


    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
}
