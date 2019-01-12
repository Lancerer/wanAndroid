package com.lancer.wanandroid.ui.web;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends BaseFragment implements View.OnClickListener {
    private static final String URL = "url";

    private static final String CONTENT = "content";

    private static final String ARTICLEID = "articleId";

    private static final String ISCOLLECT = "isCollect";
    private boolean mIscollect;
    private String mArticleId, mLink, mContent;
    private android.widget.ImageView mIvBack;
    private android.widget.TextView mTvWebTitle;
    private android.widget.ImageView mIvIntent;
    private android.widget.LinearLayout mWebview;
    private android.widget.LinearLayout mShare;
    private android.widget.LinearLayout mCollect;
    private AgentWeb mAgentWeb;

    @Override
    public int initLayout() {
        return R.layout.fragment_web;
    }

    /**
     * fragment中隐藏toolbar的方法
     */
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Log.d("webFragment", "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        Log.d("webFragment", "onStop");
    }

    @Override
    public void initView(View view) {
        mIvBack = view.findViewById(R.id.iv_back);
        mTvWebTitle = view.findViewById(R.id.tv_web_title);
        mIvIntent = view.findViewById(R.id.iv_intent);
        mWebview = view.findViewById(R.id.webview);
        mShare = view.findViewById(R.id.share);
        mCollect = view.findViewById(R.id.collect);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebview, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mLink);
        mTvWebTitle.setText(mContent);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLink = getArguments().getString(URL);
        mContent = getArguments().getString(CONTENT);
        mArticleId = getArguments().getString(ARTICLEID);
        mIscollect = getArguments().getBoolean(ISCOLLECT);
    }

    @Override
    public void initListener() {
        mIvBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                _mActivity.onBackPressed();
               /* //todo
                pop();*/
                break;
            case R.id.iv_intent:
                break;
            case R.id.share:
                break;
            case R.id.collect:
                //todo 收藏功能
                break;
            default:
                break;

        }
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    public static WebFragment newInstance(String url, String content, int id, boolean isCollect) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        args.putString(CONTENT, content);
        args.putInt(ARTICLEID, id);
        args.putBoolean(ISCOLLECT, isCollect);
        fragment.setArguments(args);
        return fragment;
    }


}
