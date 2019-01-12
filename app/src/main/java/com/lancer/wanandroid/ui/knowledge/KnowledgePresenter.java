package com.lancer.wanandroid.ui.knowledge;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.KnowledgeBean;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/7
 * desc
 *
 * @author Lancer
 */
public class KnowledgePresenter extends BasePresenter<KnowledgeView> {
    private KnowledgeView mKnowledgeView;

    @Override
    public KnowledgeView getView() {
        return super.getView();
    }

    @Override
    public void attachView(KnowledgeView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getKnowledge() {
        if (mKnowledgeView == null) {
            mKnowledgeView = getView();
            RetrofitUtils.create(ApiService.class)
                    .getKnowledge()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<KnowledgeBean>() {
                        @Override
                        public void onsuccess(KnowledgeBean response) {
                            mKnowledgeView.setKnowledge(response);
                        }
                    });
        }
    }
}
