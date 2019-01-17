package com.lancer.wanandroid.ui.navigation;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.adapter.NavigationAdapter;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.bean.NavigationBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.List;

public class NavigationFragment extends BaseFragment<NavigationView, NavigationPresenter> implements NavigationView, BaseQuickAdapter.OnItemClickListener {


    private com.zhy.view.flowlayout.TagFlowLayout mFlowlayout;
    private MyAdapter mMyAdapter;
    private android.support.v7.widget.RecyclerView mRecycleNavigation;
    private List<NavigationBean.DataBean> mLists;
    private NavigationAdapter mNavigationAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void initView(View view) {

        mFlowlayout = view.findViewById(R.id.flowlayout);
        mRecycleNavigation = view.findViewById(R.id.recycle_navigation);
    }

    @Override
    public void initData() {
        mLists = new ArrayList<>();
        mPresenter.getNavigation();
        mRecycleNavigation.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleNavigation.setAdapter(mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation_left, mLists));
        mNavigationAdapter.setOnItemClickListener(this);
    }

    @Override
    public NavigationPresenter createPresenter() {
        return new NavigationPresenter();
    }

    public static NavigationFragment newInstance() {
        Bundle args = new Bundle();
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setNavigation(NavigationBean respone) {
        mNavigationAdapter.setNewData(respone.getData());

       // mFlowlayout.setAdapter(mMyAdapter = new MyAdapter(respone.getData().get()));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private class MyAdapter extends TagAdapter<NavigationBean.DataBean.ArticlesBean> {

        private List<NavigationBean.DataBean.ArticlesBean> lists;

        public MyAdapter(List<NavigationBean.DataBean.ArticlesBean> datas) {
            super(datas);
            this.lists = datas;
        }

        @Override
        public View getView(FlowLayout parent, int position, NavigationBean.DataBean.ArticlesBean articlesBean) {
            TextView textView = (TextView) View.inflate(getContext(), R.layout.item_navigation_right, null);
            textView.setText(articlesBean.getTitle());
            return textView;
        }
    }
}
