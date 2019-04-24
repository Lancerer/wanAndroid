package com.lancer.wanandroid.ui.search;

import android.text.TextUtils;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.DataResponse;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;
import com.lancer.wanandroid.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/12
 * desc
 */
public class SearchPresenter extends BasePresenter<SearchView> {
    private SearchView mSearchView;
    private int page = 0;

    @Override
    public SearchView getView() {
        return super.getView();
    }

    @Override
    public void attachView(SearchView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getSearch(String str) {
        if (mSearchView == null) {
            mSearchView = getView();
        }
        RetrofitUtils.create(ApiService.class)
                .getSearchArticles(page, str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<DataResponse<Article>>() {
                    @Override
                    public void onsuccess(DataResponse<Article> response) {
                        if (response.getErrorCode() == 0) {
                            mSearchView.setSearch(response.getData());
                            if (TextUtils.isEmpty(response.getErrorMsg().toString())) {
                                mSearchView.setError(response.getErrorMsg().toString(),Constant.ERROR_NULL);
                            }

                        } else {
                            mSearchView.setError(response.getErrorMsg().toString(),Constant.ERROR);
                        }
                    }
                });
    }
}
