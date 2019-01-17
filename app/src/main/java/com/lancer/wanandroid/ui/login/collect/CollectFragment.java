package com.lancer.wanandroid.ui.login.collect;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.adapter.HomeAdapter;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.ui.main.MainFragment;
import com.lancer.wanandroid.ui.web.WebFragment;
import com.lancer.wanandroid.ui.web.WebPresenter;

import java.util.ArrayList;
import java.util.List;


public class CollectFragment extends BaseFragment<CollectView, CollectPresenter> implements CollectView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    private android.support.v4.widget.SwipeRefreshLayout mRefreshCollect;
    private android.support.v7.widget.RecyclerView mRecycleCollect;
    private HomeAdapter mHomeAdapter;
    private List<Article.DataBean.DatasBean> mLists;

    @Override
    public int initLayout() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initView(View view) {

        mRefreshCollect = view.findViewById(R.id.refresh_collect);
        mRecycleCollect = view.findViewById(R.id.recycle_collect);

    }

    @Override
    public void initData() {
        mLists = new ArrayList<>();
        mPresenter.getCollect();
        mRefreshCollect.setOnRefreshListener(this);
        mRecycleCollect.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleCollect.setAdapter(mHomeAdapter = new HomeAdapter(R.layout.item_home, mLists));
        mHomeAdapter.setOnItemClickListener(this);
    }

    @Override
    public CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    public static CollectFragment newInstance() {
        Bundle args = new Bundle();
        CollectFragment fragment = new CollectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setArticle(Article response) {
        mHomeAdapter.setNewData(response.getData().getDatas());
    }

    @Override
    public void OnError(String errorMsg) {
        Toast.makeText(_mActivity, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mRefreshCollect.setRefreshing(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
      start(WebFragment.newInstance(mHomeAdapter.getItem(position).getLink(),mHomeAdapter.getItem(position).getTitle(),mHomeAdapter.getItem(position).getId(),false));
    }
}
