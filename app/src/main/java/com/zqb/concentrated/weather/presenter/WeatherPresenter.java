package com.zqb.concentrated.weather.presenter;

import com.zqb.concentrated.base.App;
import com.zqb.concentrated.weather.WeatherContract;
import com.zqb.concentrated.weather.bean.CityCountyListBean;
import com.zqb.concentrated.weather.bean.CountyDaoBean;
import com.zqb.concentrated.weather.bean.CountyDaoBeanDao;
import com.zqb.concentrated.weather.net.Request;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zqb on 2017/1/16.
 */

public class WeatherPresenter implements WeatherContract.Presenter {

    @Override
    public void getWeather(final String countyName) {
        ArrayList<CountyDaoBean> countyDaoBeanList = (ArrayList<CountyDaoBean>) App.getDaoSession().getCountyDaoBeanDao().queryBuilder()
                .where(CountyDaoBeanDao.Properties.CountyName.eq(countyName)).list();
        Request.getAreaListApi().loadCityList(countyDaoBeanList.get(0).getCityPyName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityCountyListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CityCountyListBean cityCountyListBean) {
                        for (CityCountyListBean.Item item : cityCountyListBean.getItems()){
                            if (countyName.equals(item.getCityname())){
                                EventBus.getDefault().post(item);
                            }
                        }
                    }
                });

    }

    @Override
    public void getWeather(String cityName, final String countyName) {
        Request.getAreaListApi().loadCityList(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityCountyListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CityCountyListBean cityCountyListBean) {
                        for (CityCountyListBean.Item item : cityCountyListBean.getItems()){
                            if (countyName.equals(item.getCityname())){
                                EventBus.getDefault().post(item);
                            }
                        }
                    }
                });
    }

}
