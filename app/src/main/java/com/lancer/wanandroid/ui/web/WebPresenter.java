package com.lancer.wanandroid.ui.web;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.DataResponse;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/16
 * desc
 */
public class WebPresenter extends BasePresenter<WebContentView> {
    private WebContentView mWebContentView;

    @Override
    public WebContentView getView() {
        return super.getView();
    }

    @Override
    public void attachView(WebContentView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void attCollect(int id) {
        if (mWebContentView == null) {
            mWebContentView = getView();
        }
        RetrofitUtils.create(ApiService.class)
                .addCollect(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<DataResponse>() {
                    @Override
                    public void onsuccess(DataResponse response) {
                        if (response.getErrorCode() == 0) {
                            mWebContentView.onSuccess("收藏成功");
                        } else {
                            mWebContentView.onError(response.getErrorMsg());
                        }
                    }
                });

    }

    public void removeCollect(int id) {
        if (mWebContentView == null) {
            mWebContentView = getView();
        }
        RetrofitUtils.create(ApiService.class)
                .removeCollect(id, -1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<DataResponse>() {
                    @Override
                    public void onsuccess(DataResponse response) {
                        if (response.getErrorCode() == 0) {
                            mWebContentView.onSuccess("取消收藏成功");
                        } else {
                            mWebContentView.onError(response.getErrorMsg());
                        }
                    }
                });
    }
}
