package com.zqb.concentrated.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zqb.concentrated.R;
import com.zqb.concentrated.news.constant.News;
import com.zqb.concentrated.news.bean.NewsChannel;
import com.zqb.concentrated.news.bean.NewsItem;
import com.zqb.concentrated.news.adapter.NewsHeaderAdapter;
import com.zqb.concentrated.news.adapter.NewsItemAdapter;
import com.zqb.concentrated.news.presenter.NewsFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2016/9/18.
 */
@RequiresPresenter(NewsFragmentPresenter.class)
public class NewsFragment extends BeamFragment<NewsFragmentPresenter> implements
        RecyclerArrayAdapter.OnLoadMoreListener, RecyclerArrayAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView mEasyRecyclerView;

    private NewsItemAdapter mNewsItemAdapter;
    private NewsHeaderAdapter mNewsHeaderAdapter;

    private NewsChannel mNewsChannel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsChannel = (NewsChannel) getArguments().getSerializable(News.NEWSCHANNEL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mNewsItemAdapter = new NewsItemAdapter(getActivity());
        mNewsItemAdapter.setMore(R.layout.view_more, this);
        mNewsItemAdapter.setNoMore(R.layout.view_nomore);
        mNewsItemAdapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsItemAdapter.resumeMore();
            }
        });
        mNewsItemAdapter.setOnItemClickListener(this);

        mEasyRecyclerView.setProgressView(R.layout.page_progress);
        mEasyRecyclerView.setErrorView(R.layout.page_error);
        mEasyRecyclerView.getSwipeToRefresh().setColorSchemeResources(R.color.colorPrimary);

        mEasyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEasyRecyclerView.setAdapterWithProgress(mNewsItemAdapter);
        mEasyRecyclerView.setRefreshListener(this);

        getPresenter().loadNews(mNewsChannel);
    }

    public void showNews(List<NewsItem> newsItemList){
       mNewsItemAdapter.addAll(newsItemList);

    }

    public void showHeaderNews(List<NewsItem> newsItemList){
        mNewsItemAdapter.removeHeader(mNewsHeaderAdapter);
        mNewsItemAdapter.addHeader(mNewsHeaderAdapter = new NewsHeaderAdapter(getView().getContext(), newsItemList));
        mNewsItemAdapter.clear();

    }

    public void showError(){
        mEasyRecyclerView.showError();
    }

    public void hideMore(){
        mNewsItemAdapter.pauseMore();
    }

    @Override
    public void onLoadMore() {
        getPresenter().loadMoreNews(mNewsChannel);
    }

    @Override
    public void onItemClick(int position) {
        NewsDetailActivity.launch(getActivity(), mNewsItemAdapter.getAllData().get(position));
    }

    @Override
    public void onRefresh() {
        getPresenter().loadNews(mNewsChannel);
    }
}
