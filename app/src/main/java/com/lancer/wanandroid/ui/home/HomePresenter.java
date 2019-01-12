package com.lancer.wanandroid.ui.home;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.BannerBean;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;
import com.lancer.wanandroid.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/6
 * desc
 *
 * @author Lancer
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private HomeView mHomeView;
    private int mPage = 0;
    private boolean isLoadmore = false;

    @Override
    public HomeView getView() {
        return super.getView();
    }

    @Override
    public void attachView(HomeView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    /**
     * 获得网络banner方法
     */
    public void getBanner() {
        if (mHomeView == null) {
            mHomeView = getView();
            RetrofitUtils.create(ApiService.class)
                    .getBanner()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<BannerBean>() {
                        @Override
                        public void onsuccess(BannerBean response) {
                            mHomeView.setBanner(response.getData());
                        }
                    });
        }
    }

    /**
     * 获得首页文章方法
     */
    public void getArticles() {
        RetrofitUtils.create(ApiService.class)
                .getHomeArticles(mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Article>() {
                    @Override
                    public void onsuccess(Article response) {
                        if (isLoadmore) {
                            mHomeView.setHomeArticle(response, Constant.STATUS_LOAD_MORE);
                        } else {
                            mHomeView.setHomeArticle(response, Constant.STATUS_NORMAL);
                        }
                    }
                });
    }

    public void loadMore() {
        isLoadmore = true;
        mPage++;
        getArticles();
    }
}
