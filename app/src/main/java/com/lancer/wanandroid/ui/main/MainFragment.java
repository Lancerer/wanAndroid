package com.lancer.wanandroid.ui.main;


import android.os.Bundle;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.ui.home.HomeFragment;
import com.lancer.wanandroid.ui.knowledge.KnowledgeFragment;
import com.lancer.wanandroid.ui.navigation.NavigationFragment;
import com.lancer.wanandroid.ui.project.ProjectFragment;

import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends BaseFragment implements BottomNavigationBar.OnTabSelectedListener {
    private android.widget.FrameLayout mMainfragmentPool;
    private com.ashokvarma.bottomnavigation.BottomNavigationBar mBottomView;

    private int lables[] = {R.string.home, R.string.knowledge, R.string.navigation, R.string.project};

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    private SupportFragment[] mFragments = new SupportFragment[4];


    @Override
    public int initLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        mMainfragmentPool = view.findViewById(R.id.mainfragment_pool);
        mBottomView = view.findViewById(R.id.bottom_view);
        getActivity().setTitle(lables[0]);
    }

    @Override
    public void initData() {

        //初始化底部导航栏
        mBottomView.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor("#FF107FFD")
                .setInActiveColor("#cccccc")
                .setBarBackgroundColor("#ffffff");

        mBottomView.addItem(new BottomNavigationItem(R.drawable.home, R.string.home))
                .addItem(new BottomNavigationItem(R.drawable.knowledge, R.string.knowledge))
                .addItem(new BottomNavigationItem(R.drawable.navigation, R.string.navigation))
                .addItem(new BottomNavigationItem(R.drawable.project, R.string.project))
                .initialise();


        //框架Fragmentation的使用
        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = KnowledgeFragment.newInstance();
            mFragments[THIRD] = NavigationFragment.newInstance();
            mFragments[FOURTH] = ProjectFragment.newInstance();


            loadMultipleRootFragment(R.id.mainfragment_pool, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        }else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(KnowledgeFragment.class);
            mFragments[THIRD] = findFragment(NavigationFragment.class);
            mFragments[FOURTH] = findFragment(ProjectFragment.class);
        }

    }

    @Override
    public void initListener() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 切换底部导航栏监听事件
     *
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        getActivity().setTitle(lables[position]);
        showHideFragment(mFragments[position]);

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
