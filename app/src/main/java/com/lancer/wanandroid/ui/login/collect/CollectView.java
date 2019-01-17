package com.lancer.wanandroid.ui.login.collect;

import com.lancer.wanandroid.bean.Article;

/**
 * created by Lancer on 2019/1/15
 * desc
 */
public interface CollectView {
    void setArticle(Article response);

    void OnError(String errorMsg);
}
