package mohamedibrahim.networklib.services.common;

import android.support.v4.util.ArrayMap;

import mohamedibrahim.networklib.services.Exception.NoConnectionException;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class BaseServiceImpl implements BaseService {

    @Override
    public void execute(ArrayMap<String, String> params) {
        try {
            beforeExecution(params);
            if (isNetworkAvailable()) {
                fetchOnline(params);
            } else {
                handleException(new NoConnectionException("No internet connection"));
            }
        } catch (Throwable e) {
            handleException(e);
        }
    }


    protected abstract void fetchOnline(ArrayMap<String, String> params);

    protected abstract void handleException(Throwable e);

}
