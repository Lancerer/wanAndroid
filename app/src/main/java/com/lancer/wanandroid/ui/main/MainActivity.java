package com.lancer.wanandroid.ui.main;





import com.lancer.wanandroid.R;
import com.lancer.wanandroid.base.BaseActivity;
import com.lancer.wanandroid.base.BasePresenter;



/**
 * @author Lancer
 *
 */
public class MainActivity extends BaseActivity {
    private android.widget.FrameLayout mFlPool;


    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

        //启动主Fragment
        loadRootFragment(R.id.fl_pool, MainFragment.newInstance());


    }

    @Override
    public void initView() {
        mFlPool = findViewById(R.id.fl_pool);
    }


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

}
