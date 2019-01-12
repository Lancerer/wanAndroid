package com.lancer.wanandroid.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.bean.NavigationBean;

import java.util.List;

/**
 * created by Lancer on 2019/1/8
 * desc
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationBean.DataBean, BaseViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationBean.DataBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("NewApi")
    @Override
    protected void convert(BaseViewHolder helper, NavigationBean.DataBean item) {
        helper.setText(R.id.tv_item_navigation, item.getName());
    }


}
