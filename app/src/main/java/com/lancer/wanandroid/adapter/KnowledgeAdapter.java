package com.lancer.wanandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.bean.KnowledgeBean;

import java.util.List;

/**
 * created by Lancer on 2019/1/7
 * desc
 * @author Lancer
 */
public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean.DataBean, BaseViewHolder> {

    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeBean.DataBean item) {

        helper.setText(R.id.tv_title_knowledge, item.getName());

        StringBuffer stringBuffer = new StringBuffer();
        for (KnowledgeBean.DataBean.ChildrenBean childrenBean : item.getChildren()) {
            stringBuffer.append(childrenBean.getName() + "    ");
        }

        helper.setText(R.id.tv_content_knowledge, stringBuffer.toString());
    }
}
