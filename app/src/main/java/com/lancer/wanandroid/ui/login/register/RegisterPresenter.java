package com.lancer.wanandroid.ui.login.register;

import com.lancer.wanandroid.base.BasePresenter;
import com.lancer.wanandroid.bean.User;
import com.lancer.wanandroid.net.ApiService;
import com.lancer.wanandroid.net.BaseObserver;
import com.lancer.wanandroid.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * created by Lancer on 2019/1/14
 * desc
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {
    private RegisterView mRegisterView;

    @Override
    public RegisterView getView() {
        return super.getView();
    }

    @Override
    public void attachView(RegisterView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void setUser(String username, String password) {
        if (mRegisterView == null) {
            mRegisterView = getView();
        }
            RetrofitUtils.create(ApiService.class)
                    .toLogin(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<User>() {
                        @Override
                        public void onsuccess(User response) {
                            if (response.getErrorCode() == -1) {
                                mRegisterView.setErrorMsg(response.getErrorMsg());
                            } else {
                                mRegisterView.setUserInfo(response);
                            }

                        }
                    });

    }

    public void register(String username, String password) {
        if (mRegisterView == null) {
            mRegisterView = getView();
        }
        RetrofitUtils.create(ApiService.class)
                .toRegister(username, password, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<User>() {
                    @Override
                    public void onsuccess(User response) {
                        if (response.getErrorCode() == -1) {
                            mRegisterView.setErrorMsg(response.getErrorMsg());
                        } else {
                            mRegisterView.setRegister(response);
                        }

                    }
                });
    }
}
