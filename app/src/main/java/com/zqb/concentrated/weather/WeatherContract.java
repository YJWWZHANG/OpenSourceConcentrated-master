package com.zqb.concentrated.weather;

import com.zqb.concentrated.weather.BasePresenter;

/**
 * Created by zqb on 2017/1/16.
 */

public interface WeatherContract {

    interface Presenter extends BasePresenter{
        void getWeather(String countyName);
        void getWeather(String cityName, String countyName);
    }
}
