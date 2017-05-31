package com.zqb.concentrated.news.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zqb.concentrated.news.constant.News;
import com.zqb.concentrated.news.model.net.Url;
import com.zqb.concentrated.news.view.NewsFragment;

import java.util.HashMap;

/**
 * Created by admin on 2016/9/17.
 */
public class NewsFragmentPagerAdapter extends FragmentPagerAdapter{

    private HashMap<String, Fragment> mFragments;

    public NewsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new HashMap<String, Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        if (!mFragments.containsKey(Url.newsChannels.get(position).getName())){
            Fragment fragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(News.NEWSCHANNEL, Url.newsChannels.get(position));
            fragment.setArguments(bundle);
            mFragments.put(Url.newsChannels.get(position).getName(), fragment);
        }
        return mFragments.get(Url.newsChannels.get(position).getName());
    }

    @Override
    public int getCount() {
        return Url.newsChannels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Url.newsChannels.get(position).getName();
    }

    public Fragment getFragment(int position){
        return mFragments.get(position + "");
    }
}
