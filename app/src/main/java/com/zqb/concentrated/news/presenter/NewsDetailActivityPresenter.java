package com.zqb.concentrated.news.presenter;

import android.support.annotation.NonNull;

import com.jude.beam.bijection.Presenter;
import com.zqb.concentrated.news.bean.NewsDetail;
import com.zqb.concentrated.news.model.net.NewsRequest;
import com.zqb.concentrated.news.view.NewsDetailActivity;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2016/10/7.
 */
public class NewsDetailActivityPresenter extends Presenter<NewsDetailActivity>{

    @Override
    protected void onCreateView(@NonNull NewsDetailActivity view) {
        super.onCreateView(view);
    }

    public void loadNewsDetail(String postid){
        NewsRequest.getNewsApi().getNewsDetail(postid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, NewsDetail>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Map<String, NewsDetail> stringNewsDetailMap) {
                        for (String s : stringNewsDetailMap.keySet()) {
                            getView().showNewsDetail(stringNewsDetailMap.get(s));
                        }
                    }
                });
    }

}
