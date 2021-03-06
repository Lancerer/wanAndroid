package com.lancer.wanandroid.ui.knowledge;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.ISupportFragment;


public class KnowledgeListFragment extends BaseFragment {
    private static final String ID = "id";
    private static final String KNOWLEDGENAME = "knowledgename";
    private List<String> names;
    private List<Integer> ids;
    private android.support.design.widget.TabLayout mTabProject;
    private android.support.v4.view.ViewPager mVpProject;
    private String mFirstName;
    private android.widget.TextView mTvFirstName;
    private KnowListAdapter mKnowListAdapter;


    @Override
    public int initLayout() {
        return R.layout.fragment_knowledge_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ids = new ArrayList<>();
        names = new ArrayList<>();
        names = getArguments().getStringArrayList("names");
        ids = getArguments().getIntegerArrayList("ids");
        mFirstName = getArguments().getString(KNOWLEDGENAME);
    }

    @Override
    public void initView(View view) {
        mTabProject = view.findViewById(R.id.tab_project);
        mVpProject = view.findViewById(R.id.vp_project);
        mTvFirstName = view.findViewById(R.id.tv_first_name);
        mTvFirstName.setText(mFirstName);
        mTabProject.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void initData() {
        mVpProject.setAdapter(mKnowListAdapter = new KnowListAdapter(getChildFragmentManager(), names, ids));
        mVpProject.setCurrentItem(0);
        mTabProject.setupWithViewPager(mVpProject);
    }

    private class KnowListAdapter extends FragmentPagerAdapter {
        private List<String> titles;
        private List<Integer> ids;
        private List<KnowledgeArticleFragment> mFragments=new ArrayList<>();

        public KnowListAdapter(FragmentManager fm, List<String> titles, List<Integer> ids) {
            super(fm);
            this.titles = titles;
            this.ids = ids;
            for (int id : ids) {
                mFragments.add(KnowledgeArticleFragment.newInstance(id));
            }
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
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

    @Override
    public KnowledgeListPresenter createPresenter() {
        return null;
    }

    public static ISupportFragment newInstance(List<String> names, List<Integer> ids, String mFirstName) {
        Bundle args = new Bundle();
        KnowledgeListFragment fragment = new KnowledgeListFragment();
        args.putStringArrayList("names", (ArrayList<String>) names);
        args.putIntegerArrayList("ids", (ArrayList<Integer>) ids);
        args.putString(KNOWLEDGENAME, mFirstName);
        fragment.setArguments(args);
        return fragment;
    }
}
