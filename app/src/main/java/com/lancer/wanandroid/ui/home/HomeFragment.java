package com.lancer.wanandroid.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.adapter.HomeAdapter;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.BannerBean;
import com.lancer.wanandroid.ui.main.MainFragment;
import com.lancer.wanandroid.ui.web.WebFragment;
import com.lancer.wanandroid.util.Constant;
import com.lancer.wanandroid.util.MyLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {


    private android.support.v4.widget.SwipeRefreshLayout mRefreshHome;
    private android.support.v7.widget.RecyclerView mRecycleHome;
    private List<String> mBanner_title;
    private List<String> mBanner_imgpaths;
    private List<Article.DataBean.DatasBean> mArticles;
    private Banner mBanner;
    private View mBannerView;
    private HomeAdapter mHomeAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

        mRefreshHome = view.findViewById(R.id.refresh_home);
        mRecycleHome = view.findViewById(R.id.recycle_home);
    }

    @Override
    public void initData() {
        mBanner_imgpaths = new ArrayList<>();
        mBanner_title = new ArrayList<>();
        mArticles = new ArrayList<>();

        mPresenter.getBanner();
        mPresenter.getArticles();

        mRecycleHome.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleHome.setAdapter(mHomeAdapter = new HomeAdapter(R.layout.item_home, mArticles));
        /*//todo bug
        if (mBannerView.getParent() != null) {
            ((ViewGroup) mBannerView.getParent()).removeView(mBannerView);
        }*/
        mBannerView = LayoutInflater.from(getContext()).inflate(R.layout.item_bannerhead, null);
        mBanner = mBannerView.findViewById(R.id.banner);
        mHomeAdapter.addHeaderView(mBannerView);
        mHomeAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //动画默认只执行一次,如果想重复执行可设置
        mHomeAdapter.isFirstOnly(false);

    }

    @Override
    public void initListener() {
        mRefreshHome.setOnRefreshListener(this);
        mHomeAdapter.setOnLoadMoreListener(this);
        mHomeAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 设置banner的方法
     *
     * @param data
     */
    @Override
    public void setBanner(final List<BannerBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            mBanner_title.add(data.get(i).getTitle());
            mBanner_imgpaths.add(data.get(i).getImagePath());
        }

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setBannerTitles(mBanner_title);
        mBanner.setImageLoader(new MyLoader());
        mBanner.setImages(mBanner_imgpaths);
        mBanner.setDelayTime(3000);
        mBanner.isAutoPlay(true);
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        ((MainFragment) getParentFragment()).start(WebFragment.newInstance(data.get(position).getUrl(), data.get(position).getTitle(), data.get(position).getId(), false));
                    }
                })
                .start();
    }


    /**
     * 设置home页文章方法
     *
     * @param response
     * @param type
     */
    @Override
    public void setHomeArticle(Article response, int type) {

        if (type == Constant.STATUS_NORMAL) {
            //todo setNewData方法类似于add
            mHomeAdapter.setNewData(response.getData().getDatas());
            mHomeAdapter.loadMoreComplete();
        } else if (type == Constant.STATUS_LOAD_MORE) {
            //todo addData方法类似于addAll，就是加载更多
            mHomeAdapter.addData(response.getData().getDatas());
            mHomeAdapter.loadMoreComplete();
        }
    }


    /**
     * 下拉刷新方法
     */
    @Override
    public void onRefresh() {
        mRefreshHome.setRefreshing(false);
    }

    /**
     * adapter上拉加载更多方法
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    /**
     * adapter条目点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //todo
        ((MainFragment) getParentFragment()).start(WebFragment.newInstance(mHomeAdapter.getItem(position).getLink(), mHomeAdapter.getItem(position).getTitle(), mHomeAdapter.getItem(position).getId(), false));
    }
}
