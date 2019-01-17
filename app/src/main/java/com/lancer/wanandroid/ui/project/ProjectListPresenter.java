package com.lancer.wanandroid.ui.project;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.ProjectListBean;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;
import com.lancer.wanandroid.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Lancer on 2019/1/8
 * desc
 */
public class ProjectListPresenter extends BasePresenter<ProjectListView> {
    private ProjectListView mProjectListView;
    private int page = 1;
    private boolean isReflesh = false;
    private int totalPage = 5;

    @Override
    public ProjectListView getView() {
        return super.getView();
    }

    @Override
    public void attachView(ProjectListView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getProjectListArticle(int id) {
        if (mProjectListView == null) {
            mProjectListView = getView();
        }
        RetrofitUtils.create(ApiService.class)
                .getProjectList(page, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ProjectListBean>() {
                    @Override
                    public void onsuccess(ProjectListBean response) {
                        if (isReflesh) {
                            mProjectListView.setProjectListArticle(response, Constant.STATUS_LOAD_MORE);
                        } else {
                            mProjectListView.setProjectListArticle(response, Constant.STATUS_NORMAL);
                        }
                    }
                });


    }

    public void loadMore(int id) {
        if (page < totalPage && page >= 1) {
            isReflesh = true;
            page++;
            getProjectListArticle(id);
        } else {
            mProjectListView.onLoadMoreEnd();
        }

    }

}
