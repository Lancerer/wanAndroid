package com.lancer.wanandroid.ui.navigation;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.NavigationBean;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/8
 * desc
 */
public class NavigationPresenter extends BasePresenter<NavigationView> {
    private NavigationView mNavigationView;

    @Override
    public NavigationView getView() {
        return super.getView();
    }

    @Override
    public void attachView(NavigationView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getNavigation() {
        if (mNavigationView == null) {
            mNavigationView = getView();
            RetrofitUtils.create(ApiService.class)
                    .getNavigation()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<NavigationBean>() {
                        @Override
                        public void onsuccess(NavigationBean response) {
                            mNavigationView.setNavigation(response);
                        }
                    });
        }
    }
}
