package com.zqb.concentrated.news.model.net;


import com.jude.utils.JUtils;
import com.orhanobut.logger.Logger;
import com.zqb.concentrated.base.App;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2016/9/19.
 */
public class NewsRequest {

    private NewsRequest(){

    }
    //// 离线时缓存保存两个星期
    private static final long MAX_STALE = 60 * 60 * 24 * 14;
    //在线缓存在10分钟内可读取
    private static final long MAX_AGE = 60;
    //查询缓存的Cache-Control设置
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + MAX_STALE;
    //查询网络的Cache-Control设置
    private static final String CACHE_CONTROL_NETWORK = "max-age=" + MAX_STALE;

    private static NewsApi newsApi = null;

    // 云端响应头拦截器，用来配置缓存策略
    private static Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!JUtils.isNetWorkAvilable()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Logger.i("没有网络，只读缓存");
                Logger.i(request.cacheControl().toString());
            }

            Response originalResponse = null;
            try {
                originalResponse = chain.proceed(request);
            } catch (IOException e) {
                e.printStackTrace();
                Logger.e("UFO异常");
            }
            if (JUtils.isNetWorkAvilable()) {
                originalResponse.newBuilder().
                        header("Cache-Control", CACHE_CONTROL_NETWORK)
                        .removeHeader("Pragma").build();
                Logger.i("有网络，设置缓存可访问时间为：" + MAX_AGE / 60 + "分钟");
                Logger.i(request.cacheControl().toString());
            } else {
                originalResponse.newBuilder()
                        .header("Cache-Control", CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma").build();
                Logger.i("没有网络，设置缓存可访问时间为：" + MAX_STALE / 60 / 60 / 24 + "天");
                Logger.i(request.cacheControl().toString());
            }
            return originalResponse;
        }
    };

    private static Interceptor mRewriteCacheControlInterceptorT = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Logger.e("新请求", "=request==" + request.toString());
            Response response = chain.proceed(request);
            if (JUtils.isNetWorkAvilable()) {
                int maxAge = 60 * 60*24; // 有网络的时候从缓存1天后失效
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // // 无网络缓存保存四 周
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    };

    // 打印返回的json数据拦截器
    private static Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            final Request request = chain.request();
            final Response response = chain.proceed(request);

            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    Logger.e("Couldn't decode the response body; charset is likely malformed.");
                    return response;
                }
            }

            if (contentLength != 0) {
                Logger.json(buffer.clone().readString(charset));
            }

            return response;
        }
    };
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

//    private static File httpCacheDirectory = new File(App.getContext().getCacheDir(),
//            "news_item_cache");
    private static File httpCacheDirectory = new File(App.getContext().getExternalCacheDir(),
            "news_item_cache");
    private static int cacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);

    private static OkHttpClient client = new OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
            .addInterceptor(mRewriteCacheControlInterceptor)
            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(mLoggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();


    public static String getCacheControl(){
        return JUtils.isNetWorkAvilable()? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
    }

    public static NewsApi getNewsApi(){
        if (newsApi == null){
            synchronized (NewsRequest.class){
                if (newsApi == null){
                    newsApi = new Retrofit.Builder()
                            .baseUrl(Url.host)
                            .client(client)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(NewsApi.class);
                }
            }
        }
        return newsApi;
    }

}
