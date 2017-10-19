package com.xnfh.bjhospital.base;

import android.content.Context;

/**
 * Created by wangxuewei on 2017/10/17.
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){

    }
    public void onDestroy() {

    }

}