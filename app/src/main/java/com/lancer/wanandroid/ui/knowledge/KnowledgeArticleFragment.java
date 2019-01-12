package com.lancer.wanandroid.ui.knowledge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.base.BasePresenter;


public class KnowledgeArticleFragment extends BaseFragment<KnowledgeArticleView, KnowledgeArticlePresenter> {

    private static final String ID = "id";

    @Override
    public int initLayout() {
        return R.layout.fragment_knowledge_article;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public KnowledgeArticlePresenter createPresenter() {
        return new KnowledgeArticlePresenter();
    }

    public static KnowledgeArticleFragment newInstance(int id) {
        Bundle args = new Bundle();
        KnowledgeArticleFragment fragment = new KnowledgeArticleFragment();
        args.putInt(ID, id);
        return fragment;
    }
}
