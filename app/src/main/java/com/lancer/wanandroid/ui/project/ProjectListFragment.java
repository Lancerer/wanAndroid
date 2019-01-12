package com.lancer.wanandroid.ui.project;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.adapter.ProjectListAdapter;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.bean.ProjectListBean;
import com.lancer.wanandroid.ui.web.WebFragment;
import com.lancer.wanandroid.util.Constant;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectListFragment extends BaseFragment<ProjectListView, ProjectListPresenter> implements ProjectListView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    private static final String ID = "id";
    private android.support.v4.widget.SwipeRefreshLayout mRefreshProjectList;
    private android.support.v7.widget.RecyclerView mRecycleProjectList;
    private List<ProjectListBean.DataBean.DatasBean> mLists;
    private ProjectListAdapter mProjectListAdapter;
    private int id;

    @Override
    public int initLayout() {
        return R.layout.fragment_project_list;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(ID);
    }

    @Override
    public void initView(View view) {

        mRefreshProjectList = view.findViewById(R.id.refresh_project_list);
        mRecycleProjectList = view.findViewById(R.id.recycle_project_list);
    }

    @Override
    public void initData() {
        mLists = new ArrayList<>();
        mPresenter.getProjectListArticle(id);
        mRecycleProjectList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleProjectList.setAdapter(mProjectListAdapter = new ProjectListAdapter(R.layout.item_project_list, mLists));
        mProjectListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mProjectListAdapter.isFirstOnly(false);
    }

    @Override
    public void initListener() {
        mRefreshProjectList.setOnRefreshListener(this);
        mProjectListAdapter.setOnItemClickListener(this);
    }

    @Override
    public ProjectListPresenter createPresenter() {
        return new ProjectListPresenter();
    }

    public static ProjectListFragment newInstance(int id) {
        Bundle args = new Bundle();
        ProjectListFragment fragment = new ProjectListFragment();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setProjectListArticle(ProjectListBean response, int type) {
        if (type == Constant.STATUS_NORMAL) {
            mProjectListAdapter.setNewData(response.getData().getDatas());
            mProjectListAdapter.loadMoreComplete();
        } else if (type == Constant.STATUS_LOAD_MORE) {
            mProjectListAdapter.addData(response.getData().getDatas());
            mProjectListAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        mRefreshProjectList.setRefreshing(false);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        //todo 两层嵌套找到最原始的父空间
        ((SupportFragment) getParentFragment().getParentFragment()).start(WebFragment.newInstance(mProjectListAdapter.getItem(position).getLink(), mProjectListAdapter.getItem(position).getTitle(), mProjectListAdapter.getItem(position).getId(), false));
    }
}
