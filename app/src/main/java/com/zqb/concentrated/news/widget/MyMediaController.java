package com.zqb.concentrated.news.widget;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zqb.concentrated.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by admin on 2016/10/17.
 */
public class MyMediaController extends MediaController {
    private static final int HIDEFRAM = 0;//控制提示窗口的显示

    private ImageButton img_back;//返回键
    private ImageView img_Battery;//电池电量显示
    private TextView textViewTime;//时间提示
    private TextView textViewBattery;//文字显示电池

    private View mVolumeBrightnessLayout;//提示窗口
    private ImageView mOperationBg;//提示图片
    private TextView mOperationTv;//提示文字

    private SeekBar progress;

    private GestureDetector mGestureDetector;
    private int controllerWidth;
    private Activity activity;
    private VideoView videoView;
    private Context context;

    private AudioManager mAudioManager;

    //最大声音
    private int mMaxVolume;
    // 当前声音
    private int mVolume = -1;
    //当前亮度
    private float mBrightness = -1f;

    private OnClickListener backListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (activity != null){
                activity.finish();
            }
        }
    };

    private Handler myHandler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            switch (msg.what){
                case HIDEFRAM:
                    mVolumeBrightnessLayout.setVisibility(GONE);
                    mOperationTv.setVisibility(GONE);
                    break;
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    public MyMediaController(Context context, VideoView videoView, Activity activity) {
        super(context);
        this.context = context;
        this.videoView = videoView;
        this.activity = activity;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        controllerWidth = wm.getDefaultDisplay().getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        controllerWidth = dm.widthPixels;
        mGestureDetector = new GestureDetector(context, new MyGestureDetector());
    }

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            int x = (int) e2.getRawX();
            Display disp = activity.getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();
            if (mOldX > windowWidth * 3.0 / 4.0) {// 右边滑动 屏幕 3/4
                onVolumeSlide((mOldY - y) / windowHeight);
            } else if (mOldX < windowWidth * 1.0 / 4.0) {// 左边滑动 屏幕 1/4
                onBrightnessSlide((mOldY - y) / windowHeight);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0){
            mBrightness = activity.getWindow().getAttributes().screenBrightness;
            if (mBrightness < 0.00f){
                mBrightness = 0.50f;
            }
            if (mBrightness < 0.01f){
                mBrightness = 0.01f;
            }
            mVolumeBrightnessLayout.setVisibility(VISIBLE);
            mOperationTv.setVisibility(VISIBLE);
        }

        WindowManager.LayoutParams lpa = activity.getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f){
            lpa.screenBrightness = 1.0f;
        }else if (lpa.screenBrightness < 0.01f){
            lpa.screenBrightness = 0.01f;
        }
        activity.getWindow().setAttributes(lpa);
        mOperationTv.setText((int) (lpa.screenBrightness * 100) + "%");
        if (lpa.screenBrightness * 100 >= 90) {
            mOperationBg.setImageResource(R.drawable.light_100);
        } else if (lpa.screenBrightness * 100 >= 80 && lpa.screenBrightness * 100 < 90) {
            mOperationBg.setImageResource(R.drawable.light_90);
        } else if (lpa.screenBrightness * 100 >= 70 && lpa.screenBrightness * 100 < 80) {
            mOperationBg.setImageResource(R.drawable.light_80);
        } else if (lpa.screenBrightness * 100 >= 60 && lpa.screenBrightness * 100 < 70) {
            mOperationBg.setImageResource(R.drawable.light_70);
        } else if (lpa.screenBrightness * 100 >= 50 && lpa.screenBrightness * 100 < 60) {
            mOperationBg.setImageResource(R.drawable.light_60);
        } else if (lpa.screenBrightness * 100 >= 40 && lpa.screenBrightness * 100 < 50) {
            mOperationBg.setImageResource(R.drawable.light_50);
        } else if (lpa.screenBrightness * 100 >= 30 && lpa.screenBrightness * 100 < 40) {
            mOperationBg.setImageResource(R.drawable.light_40);
        } else if (lpa.screenBrightness * 100 >= 20 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.light_30);
        } else if (lpa.screenBrightness * 100 >= 10 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.light_20);
        }

    }

    private void onVolumeSlide(float percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;

            // 显示
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
            mOperationTv.setVisibility(VISIBLE);
        }

        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume) {
            index = mMaxVolume;
        }
        else if (index < 0) {
            index = 0;
        }
        int volmn = (int) (((double) index / mMaxVolume)*100);
        if (volmn >= 100) {
            mOperationBg.setImageResource(R.drawable.volmn_100);
        } else if (volmn >= 50 && volmn < 100) {
            mOperationBg.setImageResource(R.drawable.volmn_60);
        } else if (volmn > 0 && volmn < 50) {
            mOperationBg.setImageResource(R.drawable.volmn_30);
        } else {
            mOperationBg.setImageResource(R.drawable.volmn_no);
        }
        //DecimalFormat    df   = new DecimalFormat("######0.00");
        mOperationTv.setText((int) (((double) index / mMaxVolume)*100)+"%");
        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
    }

    @Override
    protected View makeControllerView() {
        View view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("mymediacontroller", "layout", getContext().getPackageName()), this);
//        v.setMinimumHeight(controllerWidth);
//        img_back = (ImageButton) v.findViewById(getResources().getIdentifier("mediacontroller_top_back", "id", context.getPackageName()));
//        img_Battery = (ImageView) v.findViewById(getResources().getIdentifier("mediacontroller_imgBattery", "id", context.getPackageName()));
//        img_back.setOnClickListener(backListener);
//        textViewBattery = (TextView)v.findViewById(getResources().getIdentifier("mediacontroller_Battery", "id", context.getPackageName()));
//        textViewTime = (TextView)v.findViewById(getResources().getIdentifier("mediacontroller_time", "id", context.getPackageName()));

//        View view = View.inflate(context, R.layout.mediacontroller, null);
        view.setMinimumHeight(controllerWidth);
        img_back = (ImageButton) view.findViewById(R.id.mediacontroller_top_back);
        img_Battery = (ImageView) view.findViewById(R.id.mediacontroller_imgBattery);
        img_back.setOnClickListener(backListener);
        textViewBattery = (TextView) view.findViewById(R.id.mediacontroller_Battery);
        textViewTime = (TextView) view.findViewById(R.id.mediacontroller_time);

        progress = (SeekBar)view.findViewById(R.id.mediacontroller_seekbar);
        //mid
        mVolumeBrightnessLayout = (RelativeLayout)view.findViewById(R.id.operation_volume_brightness);
        mOperationBg = (ImageView)view.findViewById(R.id.operation_bg);
        mOperationTv = (TextView) view.findViewById(R.id.operation_tv);
        mOperationTv.setVisibility(View.GONE);

        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        return view;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("MYApp-MyMediaController-dispatchKeyEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)){
            return true;
        }
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;
        myHandler.removeMessages(HIDEFRAM);
        myHandler.sendEmptyMessageDelayed(HIDEFRAM, 1);

    }

    private void playOrPause() {
        if (videoView != null){
            if(videoView.isPlaying()){
                videoView.pause();
            }else {
                videoView.start();
            }
        }
    }

    private void toggleMediaControlsVisiblity() {
        if (isShowing()){
            hide();
        }else {
            show();
        }
    }

    public void setTime(String time){
        if (textViewTime != null){
            textViewTime.setText(time);
        }
    }

    public void setBattery(String stringBattery){
        if (textViewBattery != null && img_Battery != null){
            textViewBattery.setText(stringBattery + "%");
            int battery = Integer.valueOf(stringBattery);
            if(battery < 15)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_15));
            if(battery < 30 && battery >= 15)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_15));
            if(battery < 45 && battery >= 30)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_30));
            if(battery < 60 && battery >= 45)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_45));
            if(battery < 75 && battery >= 60)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_60));
            if(battery < 90 && battery >= 75)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_75));
            if(battery > 90 )img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_90));
        }
    }
}
