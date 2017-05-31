package com.zqb.concentrated.weather;

import android.view.View;

/**
 * Created by zqb on 2017/1/4.
 */

public interface OnRcvItemClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
