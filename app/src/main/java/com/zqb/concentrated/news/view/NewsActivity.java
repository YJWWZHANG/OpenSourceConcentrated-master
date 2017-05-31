package com.zqb.concentrated.news.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.nineoldandroids.view.ViewHelper;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.presenter.NewsActivityPresenter;
import com.zqb.concentrated.news.adapter.NewsFragmentPagerAdapter;
import com.zqb.concentrated.weather.view.WeatherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@RequiresPresenter(NewsActivityPresenter.class)
public class NewsActivity extends BeamBaseActivity<NewsActivityPresenter> implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, NewsActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
        getWindow().setEnterTransition(transition);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        initDrawerLayout();

        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(new NewsFragmentPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);

                drawerView.setAlpha(0.1f + 0.9f * slideOffset);

                mContent.setTranslationX(drawerView.getMeasuredWidth() * slideOffset);
                mContent.setPivotX(0);
                mContent.setScaleX(0.8f + 0.2f * (1 - slideOffset));
                mContent.setScaleY(0.8f + 0.2f * (1 - slideOffset));
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
//        mNavigationView.getMenu().clear();
//        mNavigationView.getMenu().add(0, 0, 0, "测试");
//        mNavigationView.getMenu().add(0, 1, 0, "测试");
//        mNavigationView.inflateMenu(R.menu.menu_navigation);
        mNavigationView.setCheckedItem(R.id.action_news);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_news:
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
                WeatherActivity.launch(this);
                break;
            default:
                break;
        }
        return true;
    }

    @OnClick(R.id.fab_to_top)
    public void onClick() {

    }
}
