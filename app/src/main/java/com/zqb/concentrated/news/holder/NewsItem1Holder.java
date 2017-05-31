package com.zqb.concentrated.news.holder;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.bean.NewsItem;

/**
 * Created by admin on 2016/9/18.
 */
public class NewsItem1Holder extends BaseViewHolder<NewsItem> {
    SimpleDraweeView mSimpleDraweeView;
    TextView mTvNewsTitle;
    TextView mTvNewsDigest;
//    TextView tvNewsSummaryPtime;

    public NewsItem1Holder(ViewGroup parent) {
        super(parent, R.layout.news_item1);
        mSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_news_image);
        mTvNewsTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
        mTvNewsDigest = (TextView) itemView.findViewById(R.id.tv_news_digest);
//        tvNewsSummaryPtime = (TextView) itemView.findViewById(R.id.tv_news_summary_ptime);
    }

    @Override
    public void setData(NewsItem data) {
        super.setData(data);
        mTvNewsTitle.setText(data.getTitle());
        mTvNewsDigest.setText(data.getDigest());
//        tvNewsSummaryPtime.setText(data.getPtime());
        mSimpleDraweeView.setImageURI(Uri.parse(data.getImgsrc()));
    }
}
