package com.lancer.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.bean.Article;

import java.util.List;

/**
 * created by Lancer on 2019/1/6
 * desc
 */
public class HomeAdapter extends BaseQuickAdapter<Article.DataBean.DatasBean, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<Article.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DataBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_time, item.getNiceDate());
        TextView textview = helper.getView(R.id.tv_desc);
        if (TextUtils.isEmpty(item.getDesc())) {
            textview.setVisibility(View.GONE);
        } else {
            textview.setVisibility(View.VISIBLE);
            textview.setText(item.getDesc());
        }
        helper.setText(R.id.tv_tag, item.getSuperChapterName() + "/" + item.getChapterName());

    }
}
