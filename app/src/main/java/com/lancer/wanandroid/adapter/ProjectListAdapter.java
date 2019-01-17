package com.lancer.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.bean.ProjectListBean;

import java.util.List;

/**
 * created by Lancer on 2019/1/8
 * desc
 */
public class ProjectListAdapter extends BaseQuickAdapter<ProjectListBean.DataBean.DatasBean, BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<ProjectListBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListBean.DataBean.DatasBean item) {
        helper.setText(R.id.tvTitle, item.getTitle());
        helper.setText(R.id.tvContent, item.getDesc());
        helper.setText(R.id.tvAuthor, item.getAuthor());
        helper.setText(R.id.tvNiceDate, item.getNiceDate());
        ImageView articleiv = helper.getView(R.id.articleiv);
        Glide.with(mContext).load(item.getEnvelopePic()).into(articleiv);


    }
}
