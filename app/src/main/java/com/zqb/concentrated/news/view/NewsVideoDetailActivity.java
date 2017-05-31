package com.zqb.concentrated.news.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.zqb.concentrated.R;
import com.zqb.concentrated.news.bean.NewsDetail;
import com.zqb.concentrated.news.widget.MyMediaController;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class NewsVideoDetailActivity extends AppCompatActivity implements Runnable{

    private static final int BATTERY = 1;
    private static final int TIME = 0;
    @BindView(R.id.video_view)
    VideoView mVideoView;

    private NewsDetail mNewsDetail;

//    private MediaController mMediaController;
    private MyMediaController mMyMediaController;
    private String path = "http://baobab.wdjcdn.com/145076769089714.mp4";
    /**
     * 直播地址
     */
    private String zhiboPath = "http://flv3.quanmin.tv/live/3766_2616172_550.flv";
    private Uri uri;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TIME:
                    mMyMediaController.setTime(msg.obj.toString());
                    break;
                case BATTERY:
                    mMyMediaController.setBattery(msg.obj.toString());
                    break;
            }
        }
    };
    private BroadcastReceiver batteryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                Message msg = new Message();
                msg.obj = (level * 100) / scale + "";
                msg.what = BATTERY;
                mHandler.sendMessage(msg);
            }
        }
    };

    public static void startNewsVideoActivity(Context context, NewsDetail mNewsDetail) {
        Intent intent = new Intent(context, NewsVideoDetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("mNewsDetail", mNewsDetail);
//        intent.putExtras(bundle);
        intent.putExtra("mNewsDetail", mNewsDetail);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_video_detail_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        mNewsDetail = (NewsDetail) getIntent().getExtras().getSerializable("mNewsDetail");

        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this)) {
            return;
        }
//        mVideoView.setVideoPath(Environment.getExternalStorageDirectory().getAbsolutePath() +
//                        "/测试目录/37.mp4");//设置播放地址
//        Logger.w(mNewsDetail.getVideo().get(0).getMp4_url());
        mVideoView.setVideoPath(mNewsDetail.getVideo().get(0).getMp4_url());
//        mVideoView.setVideoPath(zhiboPath);
//        mMediaController = new MediaController(this);//实例化控制器
        mMyMediaController = new MyMediaController(this, mVideoView, this);
//        mMediaController.show(5000);//控制器显示5s后自动隐藏
        mMyMediaController.show(5000);
//        mVideoView.setMediaController(mMediaController);//绑定控制器
        mVideoView.setMediaController(mMyMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//设置播放画质 高画质
        mVideoView.requestFocus();//取得焦点

        registerBroadcastReceiver();

        new Thread(this).start();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void run() {
        while (true) {
            //时间读取线程
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String str = sdf.format(new Date());
            Message msg = new Message();
            msg.obj = str;
            msg.what = TIME;
            mHandler.sendMessage(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryBroadcastReceiver, intentFilter);
    }
}
