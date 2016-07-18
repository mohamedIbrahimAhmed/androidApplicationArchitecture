package mohamedibrahim.networklib.services.manager;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed.ibrahim on 6/29/2016.
 */

public final class RetrofitManager {
    private static RetrofitManager instance;
    private final Retrofit retrofit;


    private RetrofitManager(String baseUrl, OkHttpClient httpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(getAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

    }


    public static RetrofitManager init(String baseUrl, boolean allowLog, ArrayMap<String, String> headers) {
        return init(baseUrl, false, null, null, allowLog, headers);
    }

    public RetrofitManager init(String baseUrl) {
        return init(baseUrl, false, null, null, false, null);
    }


    public RetrofitManager init(String baseUrl, File cachedFile, Interceptor interceptor) {
        return init(baseUrl, cachedFile == null, interceptor, cachedFile, false, null);
    }


    public static RetrofitManager init(String baseUrl, boolean allowCache, Interceptor cacheInterceptor, File cachedFile, boolean allowLog, ArrayMap<String, String> headers) {


        if (instance != null && instance.retrofit != null)
            throw new IllegalArgumentException("instance created before");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (allowCache && cachedFile != null && cacheInterceptor != null) {
            builder.cache(getCache(cachedFile));
            builder.addInterceptor(cacheInterceptor);
        }

        if (allowLog) {
            builder.addInterceptor(getLoggingInterceptor());
        }

        if (headers != null && headers.size() > 0) {
            builder.addNetworkInterceptor(getHeaderInterceptor(headers));
        }

        instance = new RetrofitManager(baseUrl, builder.build());

        return instance;


    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    @NonNull
    private static Interceptor getHeaderInterceptor(final ArrayMap<String, String> headers) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request.Builder builder = chain.request().newBuilder();

                // add default header
                builder.addHeader("Accept", "application/json");

                for (int i = 0; i < headers.size(); i++) {
                    builder.addHeader(headers.keyAt(i), headers.valueAt(i));
                }
                return chain.proceed(builder.build());
            }
        };
    }

    private static Interceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    private RxJavaCallAdapterFactory getAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


    public <T> T create(final Class<T> service) {
        return getRetrofit().create(service);
    }


    private static Cache getCache(File cacheDir) {
        return new Cache(cacheDir, 10 * 1024 * 1024);  // 10 MB
    }


}
