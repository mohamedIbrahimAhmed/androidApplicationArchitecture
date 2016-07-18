package mohamedibrahim.networklib.services.common;

import android.support.v4.util.ArrayMap;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public interface BaseService {


    void beforeExecution(ArrayMap<String, String> params);

    void execute(ArrayMap<String, String> params);

    boolean isCanceled();

    void cancel();


    boolean isNetworkAvailable();
}
