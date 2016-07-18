package mohamedibrahim.networklib.services.AsyncHttp;

import com.loopj.android.http.AsyncHttpClient;

import mohamedibrahim.networklib.services.common.BaseServiceImpl;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class BaseAsyncHttp extends BaseServiceImpl {
    @Override
    public void cancel() {
        if (getServiceRequest() != null)
            getServiceRequest().cancelRequestsByTAG(getServiceName(), true);


    }

    @Override
    public boolean isCanceled() {
        return getServiceRequest() != null;
    }

    protected abstract AsyncHttpClient getServiceRequest();

    protected abstract String getServiceName();
}
