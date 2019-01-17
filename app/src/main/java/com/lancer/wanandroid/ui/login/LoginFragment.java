package com.lancer.wanandroid.ui.login;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.ui.login.about.AboutMeFragment;
import com.lancer.wanandroid.ui.login.collect.CollectFragment;
import com.lancer.wanandroid.ui.login.register.RegisterFragment;
import com.lancer.wanandroid.ui.main.MainFragment;
import com.lancer.wanandroid.util.Constant;
import com.lancer.wanandroid.util.Sputils;


public class LoginFragment extends BaseFragment<LoginView, LoginPresenter> implements LoginView, View.OnClickListener {


    private android.widget.TextView mTvLogin;
    private android.widget.TextView mTvCollect;
    private android.widget.TextView mTvAboutme;

    @Override
    public int initLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView(View view) {

        mTvLogin = view.findViewById(R.id.tv_login);
        mTvCollect = view.findViewById(R.id.tv_collect);
        mTvAboutme = view.findViewById(R.id.tv_aboutme);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initData() {
        if (Sputils.getBoolean(getContext(), Constant.LOGIN, false)) {
            mTvLogin.setText("登出");
        }
    }

    @Override
    public void initListener() {
        mTvAboutme.setOnClickListener(this);
        mTvCollect.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_aboutme:
                aboutme();
                break;
            case R.id.tv_collect:
                collect();
                break;
            case R.id.tv_login:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        if (Sputils.getBoolean(getContext(), Constant.LOGIN, false)) {
            Toast.makeText(_mActivity, "退出用户成功", Toast.LENGTH_SHORT).show();
            Sputils.setBoolean(getContext(), Constant.LOGIN, false);
            mTvLogin.setText("登录");
        } else {
            //todo 跳转到登录界面
            ((MainFragment) getParentFragment()).start(RegisterFragment.newInstance());
        }

    }

    private void collect() {
        if (Sputils.getBoolean(getContext(), Constant.LOGIN, false)) {
            ((MainFragment) getParentFragment()).start(CollectFragment.newInstance());
        } else {
            ((MainFragment) getParentFragment()).start(RegisterFragment.newInstance());
        }
    }

    private void aboutme() {
        ((MainFragment) getParentFragment()).start(AboutMeFragment.newInstance());
    }

    @Override
    public LoginPresenter createPresenter() {
        return null;
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
