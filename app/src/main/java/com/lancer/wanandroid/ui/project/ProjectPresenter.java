package com.lancer.wanandroid.ui.project;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.ProjectTabBean;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/7
 * desc
 */
public class ProjectPresenter extends BasePresenter<ProjectView> {
    private ProjectView mProjectView;

    @Override
    public ProjectView getView() {
        return super.getView();
    }

    @Override
    public void attachView(ProjectView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getProjectTab() {
        if (mProjectView == null) {
            mProjectView = getView();
        }
        RetrofitUtils.create(ApiService.class)
                .getProjectTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ProjectTabBean>() {
                    @Override
                    public void onsuccess(ProjectTabBean response) {
                        mProjectView.setPOrojectTab(response);
                    }
                });


    }
}
