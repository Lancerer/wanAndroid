package com.lancer.wanandroid.ui.knowledge;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.adapter.KnowledgeAdapter;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.KnowledgeBean;
import com.lancer.wanandroid.ui.home.HomeFragment;
import com.lancer.wanandroid.ui.main.MainFragment;

import java.util.ArrayList;
import java.util.List;


public class KnowledgeFragment extends BaseFragment<KnowledgeView, KnowledgePresenter> implements KnowledgeView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {


    private android.support.v4.widget.SwipeRefreshLayout mRefreshKnowledge;
    private android.support.v7.widget.RecyclerView mRecycleKnowledge;
    private List<KnowledgeBean> mList;
    private List<KnowledgeBean> KnowledgesMsg;
    private KnowledgeAdapter mKnowledgeAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void initView(View view) {
        mRefreshKnowledge = view.findViewById(R.id.refresh_knowledge);
        mRecycleKnowledge = view.findViewById(R.id.recycle_knowledge);
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        KnowledgesMsg = new ArrayList<>();
        mPresenter.getKnowledge();
        mRecycleKnowledge.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleKnowledge.setAdapter(mKnowledgeAdapter = new KnowledgeAdapter(R.layout.item_knowledge, mList));
        mKnowledgeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mKnowledgeAdapter.isFirstOnly(false);


    }

    @Override
    public void initListener() {
        mRefreshKnowledge.setOnRefreshListener(this);
        mKnowledgeAdapter.setOnItemClickListener(this);
    }

    @Override
    public KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    public static KnowledgeFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setKnowledge(List<KnowledgeBean> data) {
        mKnowledgeAdapter.setNewData(data);
        mKnowledgeAdapter.loadMoreComplete();
        KnowledgesMsg = data;
    }

    @Override
    public void onRefresh() {
        mRefreshKnowledge.setRefreshing(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<String> names = new ArrayList<String>();
        List<Integer> ids = new ArrayList<Integer>();
        //一级菜单名称
        String mFirstName = KnowledgesMsg.get(position).getName();
        for (KnowledgeBean.Children childrenBean : KnowledgesMsg.get(position).getChildren()) {
            names.add(childrenBean.getName());
            ids.add(childrenBean.getId());
        }
        ((MainFragment) getParentFragment()).start(KnowledgeListFragment.newInstance(names, ids, mFirstName));
    }
}
