package mohamedibrahim.networklib.services.common;

/**
 * Created by mohamed.ibrahim on 7/14/2016.
 */

public interface BaseServiceWithCache<T> extends BaseService {
    boolean isCached();

    T getCached();

    void CacheData(T t);
}
