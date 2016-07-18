package mohamedibrahim.networklib.services.Retrofit;

import android.text.TextUtils;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.BaseServiceImpl;
import mohamedibrahim.networklib.services.common.NetworkListener;
import retrofit2.Call;

/**
 * Created by mohamed.ibrahim on 7/11/2016.
 */

public abstract class BaseRetrofit<T> extends BaseServiceImpl {

    protected final NetworkListener<T> networkListener;

    @Override
    public boolean isCanceled() {
        return getRequestCall() != null && getRequestCall().isCanceled();

    }


    protected BaseRetrofit(NetworkListener<T> networkListener) {
        this.networkListener = networkListener;
    }

    @Override
    public void cancel() {
        Call requestCall = getRequestCall();
        if (isCanceled()) requestCall.cancel();
    }


    protected abstract Call getRequestCall();

    @Override
    protected void handleException(Throwable e) {
        if (networkListener == null) return;
        if (e == null) return;
        if (TextUtils.isEmpty(e.getMessage())) return;

        networkListener.onError(new NetworkException(e.getMessage()));
    }

}
