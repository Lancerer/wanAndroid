package com.lancer.wanandroid.ui.login.register;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.bean.User;
import com.lancer.wanandroid.util.Constant;
import com.lancer.wanandroid.util.Sputils;

public class RegisterFragment extends BaseFragment<RegisterView, RegisterPresenter> implements RegisterView {
    private android.support.design.widget.TextInputLayout mEtlayoutNum;
    private android.support.design.widget.TextInputEditText mEtNumber;
    private android.support.design.widget.TextInputLayout mEtlayoutPsd;
    private android.support.design.widget.TextInputEditText mEtPassword;
    private android.widget.Button mBtLogin;
    private android.widget.Button mBtRegister;

    @Override
    public int initLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView(View view) {

        mEtlayoutNum = view.findViewById(R.id.etlayout_num);
        mEtNumber = view.findViewById(R.id.et_number);
        mEtlayoutPsd = view.findViewById(R.id.etlayout_psd);
        mEtPassword = view.findViewById(R.id.et_password);
        mBtLogin = view.findViewById(R.id.bt_login);
        mBtRegister = view.findViewById(R.id.bt_register);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mEtNumber.getText().toString()) && !TextUtils.isEmpty(mEtPassword.getText().toString())) {
                    String number = mEtNumber.getText().toString();
                    String password = mEtPassword.getText().toString();
                    mPresenter.setUser(number, password);
                } else {
                    Toast.makeText(_mActivity, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mEtNumber.getText().toString()) && !TextUtils.isEmpty(mEtPassword.getText().toString())) {
                    String number = mEtNumber.getText().toString();
                    String password = mEtPassword.getText().toString();
                    mPresenter.register(number, password);
                } else {
                    Toast.makeText(_mActivity, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserInfo(User response) {
        Toast.makeText(_mActivity, "登陆成功", Toast.LENGTH_SHORT).show();
        Sputils.setBoolean(getContext(), Constant.LOGIN, true);
        Sputils.setString(getContext(), Constant.USERNAME, response.getUsername());
        Sputils.setString(getContext(), Constant.PASSWORD, response.getPassword());
        _mActivity.onBackPressed();
    }

    @Override
    public void setRegister(User response) {
        Toast.makeText(_mActivity, "注册成功", Toast.LENGTH_SHORT).show();
        mPresenter.setUser(response.getUsername(), response.getPassword());
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        Toast.makeText(_mActivity, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
