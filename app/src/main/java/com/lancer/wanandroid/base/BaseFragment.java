package com.lancer.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * created by Lancer on 2019/1/5
 * desc
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends SupportFragment {
    public T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(initLayout(), container, false);
        initView(rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        initListener();
    }

    public void initListener() {

    }

    public abstract int initLayout();

    public abstract void initView(View view);

    public abstract void initData();

    public abstract T createPresenter();

}
