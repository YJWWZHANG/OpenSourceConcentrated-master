package com.zqb.concentrated.weather.event;

import com.zqb.concentrated.weather.bean.AreaBean;

/**
 * Created by zqb on 2017/1/16.
 */

public class AreaListMessageEvent {

    private AreaBean mAreaBean;

    public AreaListMessageEvent(AreaBean areaBean) {
        mAreaBean = areaBean;
    }

    public AreaBean getAreaBean() {
        return mAreaBean;
    }

    public void setAreaBean(AreaBean areaBean) {
        mAreaBean = areaBean;
    }
}
