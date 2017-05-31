package com.zqb.concentrated.news.model.net;

import com.zqb.concentrated.news.bean.NewsDetail;
import com.zqb.concentrated.news.bean.NewsItem;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by admin on 2016/9/19.
 */

public interface NewsApi {



    /**
     * 请求新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     *
     * @param type      新闻类别：headline为头条,local为北京本地,fangchan为房产,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    @GET("/nc/article/{type}/{id}/{startPate}-20.html")
    Observable<Map<String, List<NewsItem>>> getNews(@Path("type") String type,
                                                    @Path("id") String id,
                                                    @Path("startPate") int startPage);


    /**
     * 新闻详情：例子：http://c.m.163.com/nc/article/BFNFMVO800034JAU/full.html
     *
     * @param postId 新闻详情的id
     * @return 被观察对象
     */
    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewsDetail(@Path("postId") String postId);

    @GET("/nc/article/{type}/{id}/{startPate}-20.html")
    Observable<Map<String, List<NewsItem>>> getNews(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("id") String id,
            @Path("startPate") int startPate);
}
