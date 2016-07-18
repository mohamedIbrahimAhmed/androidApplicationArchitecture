package mohamedibrahim.networklib.services.RxRetrofit;

import android.text.TextUtils;
import android.util.Log;

import mohamedibrahim.networklib.services.Exception.NetworkException;
import mohamedibrahim.networklib.services.common.BaseServiceImpl;
import mohamedibrahim.networklib.services.common.NetworkListener;
import rx.Subscription;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public abstract class RetrofitService<T> extends BaseServiceImpl {

    protected final NetworkListener<T> networkListener;

    protected RetrofitService(NetworkListener<T> networkListener) {
        this.networkListener = networkListener;
    }


    protected abstract Subscription getSubscription();

    @Override
    public boolean isCanceled() {
        return getSubscription() != null && getSubscription().isUnsubscribed();
    }

    @Override
    public void cancel() {
        Subscription subscription = getSubscription();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            Log.e("RX_TAG", " unsubscribe ");
        }
    }


    @Override
    protected void handleException(Throwable e) {
        if (networkListener == null) return;
        if (e == null) return;
        if (TextUtils.isEmpty(e.getMessage())) return;

        networkListener.onError(new NetworkException(e.getMessage()));
    }


}
