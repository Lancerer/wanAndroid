package com.lancer.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * created by Lancer on 2019/3/14
 * desc
 */
public abstract class BaseLazyFragment<V, T extends BasePresenter<V>> extends SupportFragment {
    /**
     * Fragment的View加载完毕的标记
     */
    protected boolean isViewInitiated;
    /**
     * Fragment对用户可见的标记
     */
    protected boolean isVisibleToUser;
    /**
     * 是否懒加载
     */
    protected boolean isDataInitiated;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 第一步,改变isViewInitiated标记
     * 当onViewCreated()方法执行时,表明View已经加载完毕,此时改变isViewInitiated标记为true,并调用lazyLoad()方法
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        //只有Fragment onCreateView好了，
        //另外这里调用一次lazyLoad(）
        prepareFetchData();
    }

    /**
     * 第二步
     * 此方法会在onCreateView(）之前执行
     * 当viewPager中fragment改变可见状态时也会调用
     * 当fragment 从可见到不见，或者从不可见切换到可见，都会调用此方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    /**
     * 第四步:定义抽象方法fetchData(),具体加载数据的工作,交给子类去完成
     */
    public abstract void fetchData();

    /**
     * 第三步:在lazyLoad()方法中进行双重标记判断,通过后即可进行数据加载
     * 第一种方法
     * 调用懒加载，getUserVisibleHint()会返回是否可见状态
     * 这是fragment实现懒加载的关键,只有fragment 可见才会调用onLazyLoad() 加载数据
     */
    private void lazyLoad() {
        if (getUserVisibleHint() && isViewInitiated && !isDataInitiated) {
            fetchData();
            isDataInitiated = true;
        }
    }

    /**
     * 第二种方法
     * 调用懒加载
     */
    public void prepareFetchData() {
        prepareFetchData(false);
    }

    /**
     * 第三步:在lazyLoad()方法中进行双重标记判断,通过后即可进行数据加载
     */
    public void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
        }
    }
}
