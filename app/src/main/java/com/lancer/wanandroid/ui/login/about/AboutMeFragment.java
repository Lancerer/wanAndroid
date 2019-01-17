package com.lancer.wanandroid.ui.login.about;


import android.os.Bundle;
import android.view.View;

import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.base.BasePresenter;


public class AboutMeFragment extends BaseFragment {


    @Override
    public int initLayout() {
        return R.layout.fragment_about_me;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    public static AboutMeFragment newInstance() {
        Bundle args = new Bundle();
        AboutMeFragment fragment = new AboutMeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
