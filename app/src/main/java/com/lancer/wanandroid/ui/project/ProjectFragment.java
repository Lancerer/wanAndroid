package com.lancer.wanandroid.ui.project;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.ProjectTabBean;
import com.lancer.wanandroid.ui.navigation.NavigationFragment;

import java.util.ArrayList;
import java.util.List;
public class ProjectFragment extends BaseFragment<ProjectView, ProjectPresenter> implements ProjectView {

    private android.support.design.widget.TabLayout mTabProject;
    private android.support.v4.view.ViewPager mVpProject;
    private MyAdapter mMyAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void initView(View view) {

        mTabProject = view.findViewById(R.id.tab_project);
        mVpProject = view.findViewById(R.id.vp_project);
    }

    @Override
    public void initData() {
        mPresenter.getProjectTab();
        mTabProject.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }


    public static ProjectFragment newInstance() {
        Bundle args = new Bundle();
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPOrojectTab(ProjectTabBean response) {
        List<ProjectTabBean.DataBean> datas = response.getData();
        List<String> names = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        for (ProjectTabBean.DataBean data : datas) {
            names.add(data.getName());
            ids.add(data.getId());
        }

        mVpProject.setAdapter(mMyAdapter = new MyAdapter(getChildFragmentManager(), names, ids));
        mVpProject.setCurrentItem(0, false);
        mTabProject.setupWithViewPager(mVpProject);

    }

    private class MyAdapter extends FragmentPagerAdapter {
        private List<String> titles;
        private List<Integer> ids;
        private List<ProjectListFragment> fragmets=new ArrayList<>();

        public MyAdapter(FragmentManager fm, List<String> titles, List<Integer> ids) {
            super(fm);
            this.titles = titles;
            this.ids = ids;
            for (Integer id : ids) {
                fragmets.add(ProjectListFragment.newInstance(id));
            }
        }

        @Override
        public Fragment getItem(int i) {
            return fragmets.get(i);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
