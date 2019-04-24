package com.lancer.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * created by Lancer on 2019/1/5
 * desc
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends SupportActivity {

    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //todo 啥意思忘记了
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        if (mPresenter != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        initView();

        //fragment重叠bug
        if (savedInstanceState == null) {
            initData();
            initListener();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public abstract int initLayout();

    public abstract void initData();

    public abstract void initView();

    public abstract T createPresenter();
}
