package com.zqb.concentrated.news.presenter;

import android.os.Handler;
import android.widget.Toast;

import com.jude.beam.expansion.BeamBasePresenter;
import com.orhanobut.logger.Logger;
import com.zqb.concentrated.base.App;
import com.zqb.concentrated.news.model.net.NewsRequest;
import com.zqb.concentrated.news.bean.NewsChannel;
import com.zqb.concentrated.news.bean.NewsItem;
import com.zqb.concentrated.news.view.NewsFragment;

import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2016/9/18.
 */
public class NewsFragmentPresenter extends BeamBasePresenter<NewsFragment> {



    public void loadNews(final NewsChannel newsChannel) {
        newsChannel.setStartPage(0);
        NewsRequest.getNewsApi().getNews(newsChannel.getType(), newsChannel.getId(), newsChannel.getStartPage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, List<NewsItem>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage().equals("HTTP 403 Forbidden")) {
                            loadNews(newsChannel);
                        } else {
                            getView().showError();
                        }
                    }

                    @Override
                    public void onNext(Map<String, List<NewsItem>> stringListMap) {
                        for (List<NewsItem> newsBeenList : stringListMap.values()) {
                            for (int i = 0; i < newsBeenList.size(); i++) {
                                if (newsBeenList.get(i).getSource().contains("网易原创")) {
                                    newsBeenList.remove(i);
                                    i--;
                                }
                            }
//                            for (int i = newsBeenList.size() - 1; i >= 0; i--){
//                                if (newsBeenList.get(i).getSource().contains("网易原创")) {
//                                    newsBeenList.remove(i);
//                                }
//                            }
                            getView().showHeaderNews(newsBeenList);
                            newsBeenList.remove(0);
                            newsBeenList.remove(0);
                            newsBeenList.remove(0);
                            newsBeenList.remove(0);
                            getView().showNews(newsBeenList);
                        }
                    }
                });
    }

    public void loadMoreNews(final NewsChannel newsChannel) {
        newsChannel.setStartPage(newsChannel.getStartPage() + 20);
//        if (getView().getNewsChannel().getStartPage() == 60){
//            adapter.stopMore();
//            return;
//        }
        NewsRequest.getNewsApi().getNews(newsChannel.getType(), newsChannel.getId(), newsChannel.getStartPage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, List<NewsItem>>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(App.getContext(), "更多加载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("网络请求处理异常" + "\n" + e.getMessage());
                        if (e.getMessage().equals("HTTP 403 Forbidden")) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getView().hideMore();
                                }
                            }, 10000);
                            newsChannel.setStartPage(newsChannel.getStartPage() - 20);
                            loadMoreNews(newsChannel);
                        } else {
                            getView().hideMore();
                        }
                    }

                    @Override
                    public void onNext(Map<String, List<NewsItem>> stringListMap) {
                        for (List<NewsItem> newsList : stringListMap.values()) {
                            for (int i = 0; i < newsList.size(); i++) {
                                if (newsList.get(i).getSource().contains("网易原创")) {
                                    newsList.remove(i);
                                    i--;
                                }
                            }
//                            for (int i = newsList.size() - 1; i >= 0; i--){
//                                if (newsList.get(i).getSource().contains("网易原创")) {
//                                    newsList.remove(i);
//                                }
//                            }
                            getView().showNews(newsList);
                        }
                    }
                });
    }
}
