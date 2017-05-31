package com.zqb.concentrated.news.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zqb.concentrated.R;
import com.zqb.concentrated.base.App;
import com.zqb.concentrated.news.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/29.
 */

public class NewsHeaderAdapter implements RecyclerArrayAdapter.ItemView {

    private Context mContext;
    private ArrayList<NewsItem> mNewsItemList = new ArrayList<NewsItem>();

    public NewsHeaderAdapter(Context context, List<NewsItem> newsBeenList) {
        this.mNewsItemList.addAll(newsBeenList);
        this.mContext = context;

    }

    @Override
    public View onCreateView(ViewGroup parent) {
        View view = View.inflate(App.getContext(), R.layout.news_header, null);
        RollPagerView rollPagerView = (RollPagerView) view.findViewById(R.id.roll_pager_view_header);
        rollPagerView.setHintView(new ColorPointHintView(mContext, Color.RED, Color.WHITE));
        rollPagerView.setAdapter(new NewsHeaderitemAdapter(mContext, mNewsItemList));
        return view;
    }

    @Override
    public void onBindView(View headerView) {
        YoYo.with(Techniques.ZoomIn).duration(700).playOn(headerView);
    }
}
