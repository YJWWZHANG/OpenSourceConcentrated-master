package com.zqb.concentrated.weather.holder;

import android.view.View;
import android.widget.TextView;

import com.zqb.concentrated.R;
import com.zqb.concentrated.weather.bean.AreaBean;
import com.zqb.concentrated.weather.bean.ProvinceDaoBean;

import butterknife.BindView;

/**
 * Created by admin on 2016/11/11.
 */

public class AreaListHolder extends BaseViewHolder<AreaBean> {

    @BindView(R.id.tv_location)
    TextView textView;

    public AreaListHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(AreaBean areaBean) {
        textView.setText(areaBean.getName());
    }
}
