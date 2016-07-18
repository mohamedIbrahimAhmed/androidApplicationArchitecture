package mohamedibrahim.networklib.services.volley;

import com.android.volley.Request;

import mohamedibrahim.networklib.services.common.BaseServiceImpl;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public abstract class VolleyBaseService extends BaseServiceImpl {


    @Override
    public void cancel() {
        if (getServiceRequest() != null)
            getServiceRequest().cancel();
    }

    @Override
    public boolean isCanceled() {
        return getServiceRequest() != null && getServiceRequest().isCanceled();
    }

    protected abstract Request getServiceRequest();
}
