package com.zqb.concentrated.weather.presenter;

import com.orhanobut.logger.Logger;
import com.zqb.concentrated.base.App;
import com.zqb.concentrated.weather.AreaListContract;
import com.zqb.concentrated.weather.bean.AreaBean;
import com.zqb.concentrated.weather.bean.CityCountyListBean;
import com.zqb.concentrated.weather.bean.CityDaoBean;
import com.zqb.concentrated.weather.bean.CityDaoBeanDao;
import com.zqb.concentrated.weather.bean.CountyDaoBean;
import com.zqb.concentrated.weather.bean.CountyDaoBeanDao;
import com.zqb.concentrated.weather.bean.ProvinceDaoBeanDao;
import com.zqb.concentrated.weather.bean.ProvinceListBean;
import com.zqb.concentrated.weather.bean.ProvinceDaoBean;
import com.zqb.concentrated.weather.constant.Constans;
import com.zqb.concentrated.weather.net.Request;
import com.zqb.concentrated.weather.view.AreaListActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by zzqqbb on 2016/11/17.
 */

public class AreaListPresenter implements AreaListContract.Presenter {

    private AreaListContract.View mView;
    private List<AreaBean> mAreaBeanList;

    public AreaListPresenter(AreaListActivity areaListActivity) {
        this.mView = areaListActivity;
        mAreaBeanList = new ArrayList<>();
    }

    @Override
    public void getProvinceList(final String address, final int areaLevel) {
        App.getDaoSession().getProvinceDaoBeanDao().queryBuilder().orderAsc(ProvinceDaoBeanDao.Properties.ProvincePyName)
                .rx().list().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ProvinceDaoBean>>() {
                    @Override
                    public void call(List<ProvinceDaoBean> provinceDaoBeanList) {
                        if (provinceDaoBeanList.size() > 0) {
                            mAreaBeanList.clear();
                            for (ProvinceDaoBean provinceDaoBean : provinceDaoBeanList) {
                                mAreaBeanList.add(new AreaBean(provinceDaoBean.getProvinceName(), provinceDaoBean.getProvincePyName()));
                            }
                            mView.showAreaList(mAreaBeanList, areaLevel);
                        } else {
                            Request.getAreaListApi().loadAreaList(address)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<ProvinceListBean>() {
                                        @Override
                                        public void onCompleted() {
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Logger.e("错误：" + e.getMessage());
                                        }

                                        @Override
                                        public void onNext(ProvinceListBean provinceListBean) {
                                            mAreaBeanList.clear();
                                            Collections.sort(provinceListBean.getItems(), new Comparator<ProvinceListBean.AreaItem>() {
                                                @Override
                                                public int compare(ProvinceListBean.AreaItem o1, ProvinceListBean.AreaItem o2) {
                                                    int num = o1.getPyName().compareTo(o2.getPyName());
                                                    return num;
                                                }
                                            });
                                            for (ProvinceListBean.AreaItem areaItem : provinceListBean.getItems()) {
                                                App.getDaoSession().getProvinceDaoBeanDao().rx().insert(new ProvinceDaoBean(null, areaItem.getQuName(), areaItem.getPyName()))
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe(new Action1<ProvinceDaoBean>() {
                                                            @Override
                                                            public void call(ProvinceDaoBean provinceDaoBean) {
                                                                App.getDaoSession().getProvinceDaoBeanDao().queryBuilder().orderAsc(ProvinceDaoBeanDao.Properties.ProvincePyName)
                                                                        .rx().list().observeOn(AndroidSchedulers.mainThread())
                                                                        .subscribe(new Action1<List<ProvinceDaoBean>>() {
                                                                            @Override
                                                                            public void call(List<ProvinceDaoBean> provinceDaoBeanList) {
                                                                                mAreaBeanList.clear();
                                                                                for (ProvinceDaoBean provinceDaoBean : provinceDaoBeanList) {
                                                                                    mAreaBeanList.add(new AreaBean(provinceDaoBean.getProvinceName(), provinceDaoBean.getProvincePyName()));
                                                                                }
                                                                                mView.showAreaList(mAreaBeanList, areaLevel);
                                                                            }
                                                                        });
                                                            }
                                                        });
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    @Override
    public void getCityCountyList(final String address, final int areaLevel) {
        if (areaLevel == Constans.CITY_LEVEL) {
            List<CityDaoBean> cityDaoBeanList = App.getDaoSession().getCityDaoBeanDao().queryBuilder().orderAsc(CityDaoBeanDao.Properties.WeatherCode)
                    .where(CityDaoBeanDao.Properties.ProvincePyName.eq(address)).list();
            if (cityDaoBeanList.size() > 0) {
                mAreaBeanList.clear();
                for (CityDaoBean cityDaoBean : cityDaoBeanList) {
                    mAreaBeanList.add(new AreaBean(cityDaoBean.getCityName(), cityDaoBean.getCityPyName()));
                }
                mView.showAreaList(mAreaBeanList, areaLevel);
                return;
            }
        }
        if (areaLevel == Constans.COUNTRY_LEVEL){
            List<CountyDaoBean> countyDaoBeanList = App.getDaoSession().getCountyDaoBeanDao().queryBuilder().orderAsc(CountyDaoBeanDao.Properties.WeatherCode)
                    .where(CountyDaoBeanDao.Properties.CityPyName.eq(address)).list();
            if (countyDaoBeanList.size() > 0){
                mAreaBeanList.clear();
                for (CountyDaoBean countyDaoBean : countyDaoBeanList){
                    mAreaBeanList.add(new AreaBean(countyDaoBean.getCountyName(), countyDaoBean.getWeatherCode()));
                }
                mView.showAreaList(mAreaBeanList, areaLevel);
                return;
            }
        }
        Request.getAreaListApi().loadCityList(address)
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
                        mAreaBeanList.clear();
                        if (areaLevel == Constans.CITY_LEVEL) {
                            ArrayList<ProvinceDaoBean> provinceDaoBeanList = (ArrayList<ProvinceDaoBean>) App.getDaoSession().getProvinceDaoBeanDao().queryBuilder()
                                    .where(ProvinceDaoBeanDao.Properties.ProvincePyName.eq(address)).list();
                            for (CityCountyListBean.Item item : cityCountyListBean.getItems()) {
                                App.getDaoSession().getCityDaoBeanDao().insert(new CityDaoBean(null, item.getCityname(),
                                        item.getPyName(), item.getUrl(),
                                        provinceDaoBeanList.get(0).getProvinceName(), provinceDaoBeanList.get(0).getProvincePyName()));
                            }
                            List<CityDaoBean> cityDaoBeanList = App.getDaoSession().getCityDaoBeanDao().queryBuilder().orderAsc(CityDaoBeanDao.Properties.WeatherCode)
                                    .where(CityDaoBeanDao.Properties.ProvincePyName.eq(address)).list();
                            if (cityDaoBeanList.size() > 0) {
                                mAreaBeanList.clear();
                                for (CityDaoBean cityDaoBean : cityDaoBeanList) {
                                    mAreaBeanList.add(new AreaBean(cityDaoBean.getCityName(), cityDaoBean.getCityPyName()));
                                }
                            }
                        } else {
                            ArrayList<CityDaoBean> cityDaoBeanList = (ArrayList<CityDaoBean>) App.getDaoSession()
                                    .getCityDaoBeanDao().queryBuilder().where(CityDaoBeanDao.Properties.CityPyName.eq(address)).list();
                            for (CityCountyListBean.Item item : cityCountyListBean.getItems()) {
                                App.getDaoSession().getCountyDaoBeanDao().insert(new CountyDaoBean(
                                        null, item.getCityname(), item.getUrl(),
                                        cityDaoBeanList.get(0).getCityName(), cityDaoBeanList.get(0).getCityPyName(),
                                        cityDaoBeanList.get(0).getProvinceName(), cityDaoBeanList.get(0).getProvincePyName()));
                            }
                            List<CountyDaoBean> countyDaoBeanList = App.getDaoSession().getCountyDaoBeanDao().queryBuilder().orderAsc(CountyDaoBeanDao.Properties.WeatherCode)
                                    .where(CountyDaoBeanDao.Properties.CityPyName.eq(address)).list();
                            if (countyDaoBeanList.size() > 0){
                                mAreaBeanList.clear();
                                for (CountyDaoBean countyDaoBean : countyDaoBeanList){
                                    mAreaBeanList.add(new AreaBean(countyDaoBean.getCountyName(), countyDaoBean.getWeatherCode()));
                                }
                            }
                        }
                        mView.showAreaList(mAreaBeanList, areaLevel);
                    }
                });
    }

}
