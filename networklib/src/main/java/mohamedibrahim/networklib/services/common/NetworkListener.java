package mohamedibrahim.networklib.services.common;

import mohamedibrahim.networklib.services.Exception.NetworkException;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public interface NetworkListener<T> {


    void onSuccess(T t);

    void onError(NetworkException e);

}


