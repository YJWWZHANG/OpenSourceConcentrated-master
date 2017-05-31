package com.zqb.concentrated.weather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zqb on 2017/1/14.
 */

@Entity
public class CountyDaoBean {
    @Id
    private Long id;

    @NotNull
    private String countyName;
//    @NotNull
//    private String countyPyName;
    @NotNull
    private String weatherCode;
    @NotNull
    private String cityName;
    @NotNull
    private String cityPyName;
    @NotNull
    private String provinceName;
    @NotNull
    private String provincePyName;
    @Generated(hash = 1467856676)
    public CountyDaoBean(Long id, @NotNull String countyName,
            @NotNull String weatherCode, @NotNull String cityName,
            @NotNull String cityPyName, @NotNull String provinceName,
            @NotNull String provincePyName) {
        this.id = id;
        this.countyName = countyName;
        this.weatherCode = weatherCode;
        this.cityName = cityName;
        this.cityPyName = cityPyName;
        this.provinceName = provinceName;
        this.provincePyName = provincePyName;
    }
    @Generated(hash = 502978538)
    public CountyDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCountyName() {
        return this.countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    public String getWeatherCode() {
        return this.weatherCode;
    }
    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityPyName() {
        return this.cityPyName;
    }
    public void setCityPyName(String cityPyName) {
        this.cityPyName = cityPyName;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getProvincePyName() {
        return this.provincePyName;
    }
    public void setProvincePyName(String provincePyName) {
        this.provincePyName = provincePyName;
    }
}
