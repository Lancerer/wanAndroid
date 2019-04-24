package com.lancer.wanandroid.ui.web;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseFragment;
import com.lancer.wanandroid.ui.login.LoginFragment;
import com.lancer.wanandroid.util.Constant;
import com.lancer.wanandroid.util.Sputils;

/**
 * a simple {@link Fragment} subclass.
 */
public class WebFragment extends BaseFragment<WebContentView, WebPresenter> implements View.OnClickListener, WebContentView {
    private static final String URL = "url";

    private static final String CONTENT = "content";

    private static final String ARTICLEID = "articleId";

    private static final String ISCOLLECT = "isCollect";
    private boolean mIscollect;
    private String mLink, mContent;
    private int mArticleId;
    private android.widget.ImageView mIvBack;
    private android.widget.TextView mTvWebTitle;
    private android.widget.ImageView mIvIntent;
    private android.widget.LinearLayout mWebview;
    private android.widget.LinearLayout mShare;
    private android.widget.LinearLayout mCollect;
    private AgentWeb mAgentWeb;
    private android.widget.ImageView mIvCollect;

    @Override
    public int initLayout() {
        return R.layout.fragment_web;
    }

    @Override
    public void initView(View view) {
        mIvBack = view.findViewById(R.id.iv_back);
        mTvWebTitle = view.findViewById(R.id.tv_web_title);
        mIvIntent = view.findViewById(R.id.iv_intent);
        mWebview = view.findViewById(R.id.webview);
        mShare = view.findViewById(R.id.share);
        mCollect = view.findViewById(R.id.collect);
        mIvCollect = view.findViewById(R.id.iv_collect);

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
        mArticleId = getArguments().getInt(ARTICLEID);
        mIscollect = getArguments().getBoolean(ISCOLLECT);
    }

    @Override
    public void initListener() {
        mIvBack.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mIvIntent.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                _mActivity.onBackPressed();
                break;
            case R.id.iv_intent:
                browser();
                break;
            case R.id.share:
                share();
                break;
            case R.id.collect:
                collect();
                break;
            default:
                break;

        }
    }

    /**
     * 手机自带浏览器打开
     */
    private void browser() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(mLink));
        startActivity(intent);
    }

    /**
     * 分享功能
     */
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, mLink);
        intent.setType("text/plain");
        startActivity(intent);
    }

    /**
     * 收藏方法
     */
    private void collect() {
        if (Sputils.getBoolean(getContext(), Constant.LOGIN, false)) {
            if (mIscollect) {
                mPresenter.removeCollect(mArticleId);
            } else {
                mPresenter.attCollect(mArticleId);
            }
        } else {
            start(LoginFragment.newInstance());
        }
    }

    @Override
    public void initData() {
        if (mIscollect) {
            mIvCollect.setImageResource(R.drawable.icon_collectsuccess);
        } else {
            mIvCollect.setImageResource(R.drawable.icon_collect);
        }
    }

    @Override
    public WebPresenter createPresenter() {
        return new WebPresenter();
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


    @Override
    public void onError(String errorMsg) {
        Toast.makeText(_mActivity, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String msg, int code) {
        if (code == Constant.COLLECT) {
            Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
            mIscollect = true;
            mIvCollect.setImageResource(R.drawable.icon_collectsuccess);
        } else if (code == Constant.UNCOLLECT) {
            Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
            mIscollect = false;
            mIvCollect.setImageResource(R.drawable.icon_collect);
        }

    }
}
