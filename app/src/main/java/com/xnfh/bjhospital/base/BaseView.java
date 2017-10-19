package com.xnfh.bjhospital.base;

/**
 * Created by wangxuewei on 2017/10/17.
 */
public interface BaseView {
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
