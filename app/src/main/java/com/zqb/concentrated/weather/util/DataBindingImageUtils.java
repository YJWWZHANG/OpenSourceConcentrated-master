package com.zqb.concentrated.weather.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.zqb.concentrated.R;
import com.zqb.concentrated.weather.constant.Constans;

/**
 * Created by zqb on 2017/1/17.
 */

public class DataBindingImageUtils {
    @BindingAdapter({"imageUrl"})
    public static void imageLoader(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(Constans.BASE_IMG_URL + url + Constans.END_IMG_URL)
                .placeholder(R.drawable.undefined).into(imageView);
    }
}
