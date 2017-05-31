package com.zqb.concentrated.weather;

import com.zqb.concentrated.weather.bean.AreaBean;
import com.zqb.concentrated.weather.bean.ProvinceDaoBean;

import java.util.List;

/**
 * Created by zqb on 2016/12/18.
 */

public interface AreaListContract {


    interface View extends BaseView{
        void showAreaList(List<AreaBean> areaBeanList, int areaLevel);
    }

    interface Presenter extends BasePresenter{
        void getProvinceList(String address, int areaLevel);
        void getCityCountyList(String address, int areaLevel);
    }
}
