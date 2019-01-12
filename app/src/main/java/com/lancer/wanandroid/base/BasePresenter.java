package com.lancer.wanandroid.base;

/**
 * created by Lancer on 2019/1/5
 * desc
 */
public class BasePresenter<V> {

    public V view;

    public V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

}
