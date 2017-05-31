package com.zqb.concentrated;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.orhanobut.logger.Logger;
import com.zqb.concentrated.weather.bean.ProvinceListBean;
import com.zqb.concentrated.weather.net.Request;

import org.junit.Test;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void weatherTest(){
        Request.getAreaListApi().loadAreaList("china")
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
                        Logger.d(provinceListBean.toString());
                    }
                });
    }
}