package com.zqb.concentrated.weather.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.orhanobut.logger.Logger;
import com.zqb.concentrated.R;
import com.zqb.concentrated.databinding.ActivityWeatherBinding;
import com.zqb.concentrated.news.view.NewsActivity;
import com.zqb.concentrated.weather.bean.AreaBean;
import com.zqb.concentrated.weather.bean.CityCountyListBean;
import com.zqb.concentrated.weather.bean.WeatherFutureBean;
import com.zqb.concentrated.weather.presenter.WeatherPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private ActivityWeatherBinding mDataBinding;
    private WeatherPresenter mWeatherPresenter;

    private HashMap<String, String> mTypeHashMap = new HashMap<String, String>(){
        {
            put("晴", "0");put("多云", "1");put("阴", "2");
            put("阵雨", "3");put("雷阵雨", "4");put("雷阵雨伴有冰雹", "5");
            put("雨夹雪", "6");put("小雨", "7");put("中雨", "8");
            put("大雨", "9");put("暴雨", "10");put("大暴雨", "11");
            put("特大暴雨", "12");put("阵雪", "13");put("小雪", "14");
            put("中雪", "15");put("大雪", "16");put("暴雪", "17");
            put("雾", "18");put("冻雨", "19");put("沙尘暴", "20");
            put("小到中雨", "21");put("中到大雨", "22");put("大到暴雨", "23");
            put("暴雨到大暴雨", "24");put("大暴雨到特大暴雨", "25");put("小到中雪", "26");
            put("中到大雪", "27");put("大到暴雪", "28");put("浮尘", "29");
            put("扬沙", "30");put("强沙尘暴", "31");put("霾", "53");
        }
    };

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, WeatherActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
        getWindow().setEnterTransition(transition);

        initView();
        initData();
        initEvent();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {

        setSupportActionBar(mToolbar);
        mToolbar.setTitle("广州市");
//        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        mNavigationView.setCheckedItem(R.id.action_weather);
    }

    private void initData() {
        mWeatherPresenter = new WeatherPresenter();
        mWeatherPresenter.getWeather("guangzhou", "广州市");
        mWeatherPresenter.getWeatherFuture("101010100");
    }

    private void initEvent() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                ViewHelper.setTranslationX(mDrawerLayout.getChildAt(0), drawerView.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
            return true;
        }
        if (itemId == R.id.action_select_city) {
            AreaListActivity.launch(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * AreaListActivity 返回数据
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAreaListEvent(AreaBean areaBean) {
        Logger.d(areaBean);
        mToolbar.setTitle(areaBean.getName());
        mWeatherPresenter.getWeather(areaBean.getName());
        mWeatherPresenter.getWeatherFuture(areaBean.getPyNameOrCode());
    }

    /**
     * 网络请求返回数据
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(CityCountyListBean.Item item) {
        Logger.d(item.toString());
        mDataBinding.setWeather(item);
    }

    /**
     * 网络请求返回数据
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherFutureEvent(WeatherFutureBean weatherFutureBean){
        Logger.d(weatherFutureBean.toString());
        mDataBinding.setYesterdayTemp(weatherFutureBean.getData().getYesterday());
//        mDataBinding.setWeatherYesterday(weatherFutureBean.getData().getYesterday());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_news:
                NewsActivity.launch(this);
                break;
            case R.id.action_book:
                break;
            case R.id.action_video:
                break;
            case R.id.action_live_tv:
                break;
            case R.id.action_music:
                break;
            case R.id.action_photo:
                break;
            case R.id.action_weather:
                break;
            default:
                break;
        }
        return true;
    }

}
