package mohamedibrahim.networklib.services.common;

import android.support.v4.util.ArrayMap;

import mohamedibrahim.networklib.services.Exception.NetworkException;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class BaseServiceCacheImpl<T> implements BaseServiceWithCache<T> {
    @Override
    public boolean isCached() {
        return true; // all service support cache by default
    }

    @Override
    public void execute(ArrayMap<String, String> params) {
        try {
            beforeExecution(params);
            if (isNetworkAvailable()) {
                fetchOnline(params);
            } else {
                getCachedOrThrowException(new NetworkException("No Internet connection..."));
            }
        } catch (Throwable e) {
            getCachedOrThrowException(e);
        }
    }

    protected abstract void fetchOnline(ArrayMap<String, String> params);

    protected abstract void getCachedOrThrowException(Throwable e);

}
