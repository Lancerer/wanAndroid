package com.lancer.wanandroid.ui.search;

import com.lancer.wanandroid.bean.Article;

/**
 * created by Lancer on 2019/1/12
 * desc
 */
public interface SearchView {
    /**
     * @param data
     */
    void setSearch(Article data);

    void setError(String errorMsg, int code);
}
