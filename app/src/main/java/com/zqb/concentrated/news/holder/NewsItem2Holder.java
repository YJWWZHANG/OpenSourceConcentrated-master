package com.zqb.concentrated.news.holder;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.bean.NewsItem;

import butterknife.BindView;

/**
 * Created by admin on 2016/9/18.
 */
public class NewsItem2Holder extends BaseViewHolder<NewsItem> {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.sdv_imgsrc1)
    SimpleDraweeView mSdvImgsrc1;
    @BindView(R.id.sdv_imgsrc2)
    SimpleDraweeView mSdvImgsrc2;
    @BindView(R.id.sdv_imgsrc3)
    SimpleDraweeView mSdvImgsrc3;

    public NewsItem2Holder(ViewGroup parent) {
        super(parent, R.layout.news_item2);
        mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        mSdvImgsrc1 = (SimpleDraweeView) itemView.findViewById(R.id.sdv_imgsrc1);
        mSdvImgsrc2 = (SimpleDraweeView) itemView.findViewById(R.id.sdv_imgsrc2);
        mSdvImgsrc3 = (SimpleDraweeView) itemView.findViewById(R.id.sdv_imgsrc3);
    }

    @Override
    public void setData(NewsItem data) {
        super.setData(data);
        mTvTitle.setText(data.getTitle());
        mSdvImgsrc1.setImageURI(Uri.parse(data.getImgsrc()));
        if (data.getImgextra() != null) {
            mSdvImgsrc2.setImageURI(Uri.parse(data.getImgextra().get(0).getImgsrc()));
            mSdvImgsrc3.setImageURI(Uri.parse(data.getImgextra().get(1).getImgsrc()));
        }
    }
}
