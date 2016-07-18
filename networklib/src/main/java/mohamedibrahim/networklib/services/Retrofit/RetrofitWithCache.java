package mohamedibrahim.networklib.services.Retrofit;

import mohamedibrahim.networklib.services.common.BaseServiceCacheImpl;
import mohamedibrahim.networklib.services.common.NetworkListener;
import retrofit2.Call;


/**
 * Created by mohamed.ibrahim on 7/11/2016.
 */

public abstract class RetrofitWithCache<T> extends BaseServiceCacheImpl<T> {

    protected final NetworkListener<T> networkListener;

    protected RetrofitWithCache(NetworkListener<T> networkListener) {
        this.networkListener = networkListener;
    }


    @Override
    public boolean isCanceled() {
        return getRequestCall() != null && getRequestCall().isCanceled();

    }

    @Override
    public void cancel() {
        Call requestCall = getRequestCall();
        if (isCanceled()) requestCall.cancel();
    }


    protected abstract Call getRequestCall();

}
