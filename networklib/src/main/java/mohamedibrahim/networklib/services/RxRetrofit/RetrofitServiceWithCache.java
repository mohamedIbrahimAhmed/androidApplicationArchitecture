package mohamedibrahim.networklib.services.RxRetrofit;

import android.util.Log;

import mohamedibrahim.networklib.services.common.BaseServiceCacheImpl;
import mohamedibrahim.networklib.services.common.NetworkListener;
import rx.Subscription;

/**
 * Created by mohamed.ibrahim on 7/4/2016.
 */

public abstract class RetrofitServiceWithCache<T> extends BaseServiceCacheImpl<T> {
    protected abstract Subscription getSubscription();

    protected final NetworkListener<T> networkListener;

    protected RetrofitServiceWithCache(NetworkListener<T> networkListener) {
        this.networkListener = networkListener;
    }

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


}
