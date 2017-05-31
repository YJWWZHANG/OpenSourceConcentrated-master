package com.zqb.concentrated.weather.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by admin on 2016/11/13.
 */

public class Request {

    private Request(){}

    private static Api areaListApi = null;
    private static Api weatherFutureApi = null;

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    public static Api getAreaListApi(){
        if (areaListApi == null){
            synchronized (Request.class){
                if (areaListApi == null){
                    areaListApi = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl("http://flash.weather.com.cn")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(SimpleXmlConverterFactory.create())
                            .build().create(Api.class);
                }
            }
        }
        return areaListApi;
    }

    public static Api getWeatherFutureApi(){
        if (weatherFutureApi == null){
            synchronized (Request.class){
                if (weatherFutureApi == null){
                    weatherFutureApi = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl("http://wthrcdn.etouch.cn")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(Api.class);
                }
            }
        }
        return weatherFutureApi;
    }
}
