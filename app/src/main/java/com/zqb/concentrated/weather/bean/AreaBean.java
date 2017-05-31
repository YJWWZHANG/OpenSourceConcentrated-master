package com.zqb.concentrated.weather.bean;

/**
 * Created by zqb on 2017/1/4.
 */

public class AreaBean {

    private String name;
    private String pyNameOrCode;

    public AreaBean(String name, String pyNameOrCode) {
        this.name = name;
        this.pyNameOrCode = pyNameOrCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPyNameOrCode() {
        return pyNameOrCode;
    }

    public void setPyNameOrCode(String pyNameOrCode) {
        this.pyNameOrCode = pyNameOrCode;
    }

    @Override
    public String toString() {
        return "AreaBean{" +
                "name='" + name + '\'' +
                ", pyNameOrCode='" + pyNameOrCode + '\'' +
                '}';
    }
}
