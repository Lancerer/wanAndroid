package com.lancer.wanandroid.ui.login.collect;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/15
 * desc
 */
public class CollectPresenter extends BasePresenter<CollectView> {
    private CollectView mCollectView;
    private int mPage = 0;

    @Override
    public CollectView getView() {
        return super.getView();
    }

    @Override
    public void attachView(CollectView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getCollect() {
        if (mCollectView == null) {
            mCollectView = getView();
        }
            RetrofitUtils.create(ApiService.class)
                    .getCollects(mPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<Article>() {
                        @Override
                        public void onsuccess(Article response) {
                            if(response.getErrorCode()==0){
                                mCollectView.setArticle(response);
                            }else {
                                mCollectView.OnError(response.getErrorMsg());
                            }

                        }
                    });

    }
}
