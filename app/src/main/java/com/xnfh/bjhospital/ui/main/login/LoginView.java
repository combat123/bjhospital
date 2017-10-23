package com.xnfh.bjhospital.ui.main.login;

import com.xnfh.bjhospital.base.BaseView;

/**
 * Created by wangxuewei on 2017/10/23.
 */
public interface LoginView extends BaseView {
    @Override
    public void showLoading(String title);

    @Override
    public void stopLoading();

    @Override
    public void showErrorTip(String msg);

    void loginSuccess(String token);
    void loginError(String errorMsg);
}
