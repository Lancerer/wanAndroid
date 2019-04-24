package com.lancer.wanandroid.ui.knowledge;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.DataResponse;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/7
 * desc
 */
public class KnowledgeArticlePresenter extends BasePresenter<KnowledgeArticleView> {
    private KnowledgeArticleView mKnowledgeArticleView;
    private int page = 0;

    @Override
    public KnowledgeArticleView getView() {
        return super.getView();
    }

    @Override
    public void attachView(KnowledgeArticleView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getArticle(int id) {
        if (mKnowledgeArticleView == null) {
            mKnowledgeArticleView = getView();
        }
            RetrofitUtils.create(ApiService.class)
                    .getKnowledgeArticle(page, id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<DataResponse<Article>>() {

                        @Override
                        public void onsuccess(DataResponse<Article> response) {
                            mKnowledgeArticleView.setArtilce(response.getData());
                        }
                    });

    }

}
