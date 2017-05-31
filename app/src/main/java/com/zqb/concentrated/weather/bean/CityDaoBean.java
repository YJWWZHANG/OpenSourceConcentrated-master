package com.zqb.concentrated.weather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zqb on 2017/1/11.
 */

@Entity
public class CityDaoBean {
    @Id
    private Long id;
    @NotNull
    private String cityName;
    @NotNull
    private String cityPyName;
    @NotNull
    private String weatherCode;
    @NotNull
    private String provinceName;
    @NotNull
    private String provincePyName;
    @Generated(hash = 1688647922)
    public CityDaoBean(Long id, @NotNull String cityName,
            @NotNull String cityPyName, @NotNull String weatherCode,
            @NotNull String provinceName, @NotNull String provincePyName) {
        this.id = id;
        this.cityName = cityName;
        this.cityPyName = cityPyName;
        this.weatherCode = weatherCode;
        this.provinceName = provinceName;
        this.provincePyName = provincePyName;
    }
    @Generated(hash = 615817619)
    public CityDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getWeatherCode() {
        return this.weatherCode;
    }
    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
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
