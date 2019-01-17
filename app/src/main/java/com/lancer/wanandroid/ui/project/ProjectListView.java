package com.lancer.wanandroid.ui.project;

import com.lancer.wanandroid.bean.ProjectListBean;

/**
 * created by Lancer on 2019/1/8
 * desc
 */
public interface ProjectListView {

    /**
     * p
     * @param response
     * @param type
     */
    void setProjectListArticle(ProjectListBean response, int type);

    /**
     * 没有更多数据加载方法
     */
    void onLoadMoreEnd();

}
