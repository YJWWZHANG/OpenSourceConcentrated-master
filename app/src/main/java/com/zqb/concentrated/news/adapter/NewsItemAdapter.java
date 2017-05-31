package com.zqb.concentrated.news.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zqb.concentrated.news.bean.NewsItem;
import com.zqb.concentrated.news.holder.NewsItem1Holder;
import com.zqb.concentrated.news.holder.NewsItem2Holder;

/**
 * Created by admin on 2016/9/20.
 */
public class NewsItemAdapter extends RecyclerArrayAdapter<NewsItem> {

    public final static int ITEM1_TYPE = 1;
    public final static int ITEM2_TYPE = 2;


    public NewsItemAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM1_TYPE){
            return new NewsItem1Holder(parent);
        }else {
            return new NewsItem2Holder(parent);
        }
    }

    @Override
    public int getViewType(int position) {
        if (getAllData().get(position).getImgextra() == null){
            return ITEM1_TYPE;
        }else {
            return ITEM2_TYPE;
        }
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
//        ViewHelper.setScaleX(holder.itemView, 0.5f);
//        ViewHelper.setScaleY(holder.itemView, 0.5f);
//        ViewPropertyAnimator.animate(holder.itemView).scaleX(1).setDuration(350).start();
//        ViewPropertyAnimator.animate(holder.itemView).scaleY(1).setDuration(350).start();
        YoYo
//                .with(Techniques.RotateInUpLeft)
                .with(Techniques.ZoomInRight)
                .duration(700)
                .playOn(holder.itemView);
}
}
