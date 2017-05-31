package com.zqb.concentrated.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zqb.concentrated.R;
import com.zqb.concentrated.weather.OnRcvItemClickListener;
import com.zqb.concentrated.weather.bean.AreaBean;
import com.zqb.concentrated.weather.holder.AreaListHolder;
import com.zqb.concentrated.weather.holder.BaseViewHolder;

import java.util.List;


/**
 * Created by admin on 2016/11/11.
 */

public class AreaListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<AreaBean> mAreaBeanList;
    private OnRcvItemClickListener mOnRcvItemClickListener;

    public AreaListAdapter(Context context) {
        this.mContext = context;
    }

    public List<AreaBean> getData() {
        return mAreaBeanList;
    }

    public void setData(List<AreaBean> areaBeanList) {
        mAreaBeanList = areaBeanList;
        notifyDataSetChanged();
    }

    public void setOnRcvItemClickListener(OnRcvItemClickListener onRcvItemClickListener) {
        mOnRcvItemClickListener = onRcvItemClickListener;
    }

    @Override
    public AreaListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_area_list, parent, false);
        return new AreaListHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,int position) {
        ((BaseViewHolder)holder).bind(mAreaBeanList.get(position));
        if (!holder.itemView.hasOnClickListeners()){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRcvItemClickListener.onClick(v, holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnRcvItemClickListener.onLongClick(v, holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mAreaBeanList == null){
            return 0;
        }
        return mAreaBeanList.size();
    }
}
