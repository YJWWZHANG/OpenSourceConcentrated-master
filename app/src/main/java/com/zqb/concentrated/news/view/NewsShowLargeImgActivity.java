package com.zqb.concentrated.news.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.constant.News;
import com.zqb.concentrated.news.bean.NewsDetail;
import com.zqb.concentrated.news.presenter.NewsShowLargeImgActivityPresenter;
import com.zqb.concentrated.news.adapter.NewsShowLargeImgAdapter;
import com.zqb.concentrated.news.widget.PinchImageViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@RequiresPresenter(NewsShowLargeImgActivityPresenter.class)
public class NewsShowLargeImgActivity extends BeamBaseActivity<NewsShowLargeImgActivityPresenter> {

    @BindView(R.id.pivp_image_detail)
    PinchImageViewPager mPivpImageDetail;

    private NewsDetail mNewsDetail;

    public static void startNewsImageDetailActivity(Context context, NewsDetail newsDetail) {
        Intent intent = new Intent(context, NewsShowLargeImgActivity.class);
        intent.putExtra(News.NEWSDETAIL, newsDetail);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_image_detail_activity);
        ButterKnife.bind(this);
        mNewsDetail = (NewsDetail) getIntent().getExtras().getSerializable(News.NEWSDETAIL);


        mPivpImageDetail.setAdapter(new NewsShowLargeImgAdapter(this, mNewsDetail));

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
