package com.lancer.wanandroid.ui.knowledge;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.KnowledgeBean;
import com.lancer.wanandroid.ui.main.MainFragment;
import com.lancer.wanandroid.ui.web.WebFragment;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;


public class KnowledgeArticleFragment extends BaseFragment<KnowledgeArticleView, KnowledgeArticlePresenter> implements KnowledgeArticleView, SwipeRefreshLayout.OnRefreshListener {

    private static final String ID = "id";
    private android.support.v4.widget.SwipeRefreshLayout mRefreshKnowledgeArticle;
    private android.support.v7.widget.RecyclerView mRecycleKnowledgeArticle;
    private int mId;
    private HomeAdapter mHomeAdapter;
    private List<Article.DataBean.DatasBean> mList;

    @Override
    public int initLayout() {
        return R.layout.fragment_knowledge_article;
    }

    @Override
    public void initView(View view) {

        mRefreshKnowledgeArticle = view.findViewById(R.id.refresh_knowledge_article);
        mRecycleKnowledgeArticle = view.findViewById(R.id.recycle_knowledge_article);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getInt(ID);
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mPresenter.getArticle(mId);
        mRefreshKnowledgeArticle.setOnRefreshListener(this);
        mRecycleKnowledgeArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleKnowledgeArticle.setAdapter(mHomeAdapter = new HomeAdapter(R.layout.item_home, mList));

    }

    @Override
    public void initListener() {
        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((KnowledgeListFragment) getParentFragment()).start(WebFragment.newInstance(mHomeAdapter.getItem(position).getLink(), mHomeAdapter.getItem(position).getTitle(), mHomeAdapter.getItem(position).getId(), false));
            }
        });
    }

    @Override
    public KnowledgeArticlePresenter createPresenter() {
        return new KnowledgeArticlePresenter();
    }

    public static KnowledgeArticleFragment newInstance(int id) {
        Bundle args = new Bundle();
        KnowledgeArticleFragment fragment = new KnowledgeArticleFragment();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setArtilce(Article response) {
        mHomeAdapter.setNewData(response.getData().getDatas());
    }

    @Override
    public void onRefresh() {
        mRefreshKnowledgeArticle.setRefreshing(false);
    }
}
