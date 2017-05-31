package com.zqb.concentrated.news.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.bean.NewsDetail;
import com.zqb.concentrated.news.widget.PinchImageView;

/**
 * Created by admin on 2016/11/5.
 */
public class NewsShowLargeImgAdapter extends PagerAdapter implements View.OnClickListener{

    private Activity mActivity;
    private NewsDetail mNewsDetail;

    public NewsShowLargeImgAdapter(Activity activity, NewsDetail newsDetail) {
        this.mActivity = activity;
        this.mNewsDetail = newsDetail;
    }

    @Override
    public int getCount() {
        return mNewsDetail.getImg().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mActivity, R.layout.news_item_large_img, null);
        PinchImageView mPinchImageView = (PinchImageView) view.findViewById(R.id.photoView);
        mPinchImageView.setOnClickListener(this);

        Glide.with(mActivity).load(mNewsDetail.getImg().get(position).getSrc()).into(mPinchImageView);

        container.addView(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        mActivity.finish();
    }
}
