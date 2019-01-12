package com.lancer.wanandroid.ui.home;

import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.BannerBean;

import java.util.List;

/**
 * created by Lancer on 2019/1/6
 * desc
 */
public interface HomeView {

    /**
     * @param data
     */
    void setBanner(List<BannerBean.DataBean> data);

    /**
     * @param response
     * @param type
     */
    void setHomeArticle(Article response, int type);
}
