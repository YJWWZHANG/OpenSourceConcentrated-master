package com.zqb.concentrated.weather.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.zqb.concentrated.R;
import com.zqb.concentrated.weather.AreaListContract;
import com.zqb.concentrated.weather.OnRcvItemClickListener;
import com.zqb.concentrated.weather.adapter.AreaListAdapter;
import com.zqb.concentrated.weather.bean.AreaBean;
import com.zqb.concentrated.weather.constant.Constans;
import com.zqb.concentrated.weather.presenter.AreaListPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaListActivity extends AppCompatActivity implements AreaListContract.View {

    @BindView(R.id.rcv_area)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private AreaListContract.Presenter mPresenter;
    private AreaListAdapter mAreaListAdapter;

    private List<AreaBean> mProvinceList = new ArrayList<>();
    private List<AreaBean> mCityList = new ArrayList<>();
    private List<AreaBean> mCountryList = new ArrayList<>();

    private int mCurrentLevel = Constans.PROVINCE_LEVEL;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AreaListActivity.class);
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
//        activity.startActivityForResult(intent, Constans.REQUEST_CODE_WEATHER, ActivityOptions.makeSceneTransitionAnimation((Activity) activity).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(explode);

        setContentView(R.layout.activity_area_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAreaListAdapter = new AreaListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAreaListAdapter);
        mAreaListAdapter.setOnRcvItemClickListener(new OnRcvItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (mCurrentLevel == Constans.PROVINCE_LEVEL) {
                    mPresenter.getCityCountyList(mAreaListAdapter.getData().get(position).getPyNameOrCode(), Constans.CITY_LEVEL);
                }else if (mCurrentLevel == Constans.CITY_LEVEL){
                    mPresenter.getCityCountyList(mAreaListAdapter.getData().get(position).getPyNameOrCode(), Constans.COUNTRY_LEVEL);
                } else {
                    EventBus.getDefault().postSticky(mAreaListAdapter.getData().get(position));
                    finishAfterTransition();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });

        mPresenter = new AreaListPresenter(this);
        mPresenter.getProvinceList(Constans.CHINA, Constans.PROVINCE_LEVEL);

    }

    @Override
    public void onBackPressed() {
        back();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            back();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showAreaList(List<AreaBean> areaBeanList, int areaLevel) {
        if (areaLevel == Constans.PROVINCE_LEVEL){
            for (AreaBean areaBean : areaBeanList) {
                mProvinceList.add(areaBean);
            }
        }else if (areaLevel == Constans.CITY_LEVEL){
            for (AreaBean areaBean : areaBeanList){
                mCityList.add(areaBean);
            }
        }else {
            for (AreaBean areaBean : areaBeanList) {
                mCountryList.add(areaBean);
            }
        }
        mCurrentLevel = areaLevel;
        setTitle();
        mAreaListAdapter.setData(areaBeanList);
    }

    private void back() {
        if (mCurrentLevel == Constans.PROVINCE_LEVEL) {
            finishAfterTransition();
        }else if (mCurrentLevel == Constans.CITY_LEVEL){
            mAreaListAdapter.setData(mProvinceList);
            mCurrentLevel = Constans.PROVINCE_LEVEL;
        }
        else {
            mAreaListAdapter.setData(mCityList);
            mCurrentLevel = Constans.CITY_LEVEL;
        }
        setTitle();
    }

    private void setTitle() {
        if (mCurrentLevel == Constans.PROVINCE_LEVEL){
            mToolbar.setTitle("选择省份");
        }else if (mCurrentLevel == Constans.CITY_LEVEL){
            mToolbar.setTitle("选择城市");
        }else {
            mToolbar.setTitle("选择区/县");
        }
    }

}
