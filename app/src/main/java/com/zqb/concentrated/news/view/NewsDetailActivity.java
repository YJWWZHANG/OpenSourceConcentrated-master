package com.zqb.concentrated.news.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.constant.News;
import com.zqb.concentrated.news.bean.NewsDetail;
import com.zqb.concentrated.news.bean.NewsItem;
import com.zqb.concentrated.news.presenter.NewsDetailActivityPresenter;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@RequiresPresenter(NewsDetailActivityPresenter.class)
public class NewsDetailActivity extends BeamBaseActivity<NewsDetailActivityPresenter> implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_news_detail_title)
    TextView mTvNewsDetailTitle;
    @BindView(R.id.tv_news_detail_ptime)
    TextView mTvNewsDetailPtime;
    @BindView(R.id.htv_news_detail_body)
    HtmlTextView mHtvNewsDetailBody;
    @BindView(R.id.sdv_news_detail_image)
    ImageView mSdvNewsDetailImage;
    @BindView(R.id.sdv_news_detail_play)
    ImageView mSdvNewsDetailPlay;
    @BindView(R.id.tv_news_detail_image_count)
    TextView mTvNewsDetailImageCount;

    private NewsItem mNewsItem;

    private int selectTextSizeItem = 1;
    private int selectTextSizeItemTemp = 1;

    private boolean isToVideo = false;
    private boolean isToLargeImg = false;

    private NewsDetail mNewsDetail;

    public static void launch(Context context, NewsItem newsItem) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(News.NEWSITEM, newsItem);
//        intent.putExtras(bundle);
        intent.putExtra(News.NEWSITEM, newsItem);
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_activity);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
        getWindow().setEnterTransition(transition);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mNewsItem = (NewsItem) getIntent().getExtras().getSerializable(News.NEWSITEM);

        getPresenter().loadNewsDetail(mNewsItem.getPostid());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finishAfterTransition();
                break;
            case R.id.action_text_size:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//                dialog.setTitle("选择文字大小");
                dialog.setSingleChoiceItems(new CharSequence[]{"大", "中", "小"}, selectTextSizeItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectTextSizeItemTemp = which;
                    }
                });
//                dialog.setCancelable(false);    //点击屏幕隐藏对话框禁止
                dialog.setPositiveButton("确定", new AlertDialog.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectTextSizeItem = selectTextSizeItemTemp;
                        switch (selectTextSizeItem){
                            case 0:
                                mHtvNewsDetailBody.setTextSize(18);
                                break;
                            case 1:
                                mHtvNewsDetailBody.setTextSize(16);
                                break;
                            case 2:
                                mHtvNewsDetailBody.setTextSize(14);
                                break;
                            default:
                                break;
                        }
                    }
                });
//                dialog.setNegativeButton("取消", new AlertDialog.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
                dialog.show();
                break;
            case R.id.action_browser_open:
                if (mNewsDetail != null){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mNewsDetail.getShareLink()));
                    startActivity(intent);
                }
                break;
            case R.id.action_share:
                showShare();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

    public void showNewsDetail(NewsDetail newsDetail) {
        this.mNewsDetail = newsDetail;
        mTvNewsDetailTitle.setText(newsDetail.getTitle());
        mTvNewsDetailPtime.setText("来源：" + newsDetail.getSource() +
                "  " + newsDetail.getPtime());
        mHtvNewsDetailBody.setHtml(newsDetail.getBody());
        Glide.with(this).load(mNewsItem.getImgsrc()).into(mSdvNewsDetailImage);
        if (newsDetail.getImg().size() > 1) {
            isToLargeImg = true;
            mTvNewsDetailImageCount.setText("共" + newsDetail.getImg().size() + "张");
            mTvNewsDetailImageCount.setVisibility(View.VISIBLE);
            mSdvNewsDetailImage.setOnClickListener(this);
        }
        if (newsDetail.getVideo().size() != 0) {
            isToVideo = true;
            mSdvNewsDetailPlay.setVisibility(View.VISIBLE);
            mTvNewsDetailImageCount.setVisibility(View.GONE);
            mSdvNewsDetailImage.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (isToVideo && mNewsDetail != null) {
            NewsVideoDetailActivity.startNewsVideoActivity(this, mNewsDetail);
        }
        if (isToLargeImg && mNewsDetail != null) {
            NewsShowLargeImgActivity.startNewsImageDetailActivity(this, mNewsDetail);
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mNewsDetail.getTitle());
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mNewsDetail.getShareLink());
        // text是分享文本，所有平台都需要这个字段
        oks.setText("来自开源秘集");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

}
