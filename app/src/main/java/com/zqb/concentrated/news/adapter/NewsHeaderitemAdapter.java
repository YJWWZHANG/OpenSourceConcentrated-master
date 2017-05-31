package com.zqb.concentrated.news.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.bean.NewsItem;
import com.zqb.concentrated.news.view.NewsDetailActivity;

import java.util.List;

/**
 * Created by admin on 2016/9/28.
 */
public class NewsHeaderitemAdapter extends StaticPagerAdapter {
    private Context mContext;
    private List<NewsItem> mNewsItemList;

    public NewsHeaderitemAdapter(Context context, List<NewsItem> newsItemList) {
        this.mContext = context;
        this.mNewsItemList = newsItemList;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        View view = View.inflate(mContext, R.layout.news_header_item, null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sdv_header_iamge);
        TextView textView = (TextView) view.findViewById(R.id.tv_header_title);
        simpleDraweeView.setImageURI(Uri.parse(mNewsItemList.get(position).getImgsrc()));
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.launch(mContext, mNewsItemList.get(position));
            }
        });
        textView.setText(mNewsItemList.get(position).getTitle());
        return view;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
