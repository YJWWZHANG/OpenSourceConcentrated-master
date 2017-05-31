package com.zqb.concentrated.weather.db;

import com.zqb.concentrated.base.App;
import com.zqb.concentrated.weather.bean.ProvinceListBean;
import com.zqb.concentrated.weather.bean.ProvinceDaoBean;
import com.zqb.concentrated.weather.bean.ProvinceDaoBeanDao;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by zqb on 2016/12/19.
 */
public class DbDataSource {

    private DbDataSource(){}

    public static void addProvince(ProvinceListBean.AreaItem areaItem, final AddCallback<ProvinceDaoBean> addCallback) {
        App.getDaoSession().getProvinceDaoBeanDao().rx().insert(new ProvinceDaoBean(null, areaItem.getQuName(), areaItem.getPyName()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ProvinceDaoBean>() {
                    @Override
                    public void call(ProvinceDaoBean provinceDaoBean) {
                        addCallback.addProvinceCompleted(provinceDaoBean);
                    }
                });
    }

    public static void queryProvince(final QueryCallback<ProvinceDaoBean> queryCallback) {
        App.getDaoSession().getProvinceDaoBeanDao().queryBuilder().orderAsc(ProvinceDaoBeanDao.Properties.ProvincePyName).rx()
                .list().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ProvinceDaoBean>>() {
                    @Override
                    public void call(List<ProvinceDaoBean> provinceDaoBeanList) {
                        queryCallback.QueryProvinceCompleted(provinceDaoBeanList);
                    }
                });
    }

    public static void deleteProvince(ProvinceDaoBean provinceDaoBean) {
        App.getDaoSession().getProvinceDaoBeanDao().rx().deleteByKey(provinceDaoBean.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });
    }

}
