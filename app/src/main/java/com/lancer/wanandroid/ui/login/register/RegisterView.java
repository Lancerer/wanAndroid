package com.lancer.wanandroid.ui.login.register;

import com.lancer.wanandroid.bean.User;

/**
 * created by Lancer on 2019/1/14
 * desc
 */
public interface RegisterView {

    void setUserInfo(User response);

    void setRegister(User response);

    void setErrorMsg(String errorMsg);
}
