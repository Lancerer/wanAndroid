package com.lancer.wanandroid.ui.search;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.adapter.HomeAdapter;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.ui.web.WebFragment;
import com.lancer.wanandroid.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lancer
 */
public class SearchFragment extends BaseFragment<SearchView, SearchPresenter> implements SearchView {

    private android.widget.ImageView mIvBackSearch;
    private EditText mEtSearch;
    private android.widget.TextView mTvSearch;
    private android.support.v7.widget.RecyclerView mRecycleSearch;
    private HomeAdapter mHomeAdapter;
    private List<Article.DataBean.DatasBean> mLists;

    @Override
    public int initLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView(View view) {
        mIvBackSearch = view.findViewById(R.id.iv_back_search);
        mEtSearch = view.findViewById(R.id.et_search);
        mTvSearch = view.findViewById(R.id.tv_search);
        mRecycleSearch = view.findViewById(R.id.recycle_search);

    }

    @Override
    public void initData() {
        mLists = new ArrayList<>();
        mRecycleSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleSearch.setAdapter(mHomeAdapter = new HomeAdapter(R.layout.item_home, mLists));
    }

    @Override
    public void initListener() {
        mIvBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEtSearch.getText().toString();
                if (TextUtils.isEmpty(mEtSearch.getText().toString())) {

                    return;
                }
                mPresenter.getSearch(content);
            }
        });
        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(WebFragment.newInstance(mHomeAdapter.getItem(position).getLink(), mHomeAdapter.getItem(position).getTitle(), mHomeAdapter.getItem(position).getId(), false));
            }
        });
    }

    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setSearch(Article data) {
        mHomeAdapter.setNewData(data.getData().getDatas());
    }

    @Override
    public void setError(String errorMsg, int error) {
        if (error == Constant.ERROR) {
            Toast.makeText(_mActivity, errorMsg, Toast.LENGTH_SHORT).show();
        } else if (error == Constant.ERROR_NULL) {
            Toast.makeText(_mActivity, "暂无相关数据", Toast.LENGTH_SHORT).show();
        }

    }


}
