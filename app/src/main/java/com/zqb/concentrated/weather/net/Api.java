package com.zqb.concentrated.weather.net;

import com.zqb.concentrated.weather.bean.CityCountyListBean;
import com.zqb.concentrated.weather.bean.ProvinceListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by admin on 2016/11/13.
 */

public interface Api {

    /**
     * http://flash.weather.com.cn/wmaps/xml/china.xml
     */
//    @GET("/zqbServer/weather/{area}.xml")
    @GET("http://flash.weather.com.cn/wmaps/xml/{area}.xml")
    Observable<ProvinceListBean> loadAreaList(@Path("area") String area);
    @GET("http://flash.weather.com.cn/wmaps/xml/{area}.xml")
    Observable<CityCountyListBean> loadCityList(@Path("area") String area);

}
