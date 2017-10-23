package com.xnfh.bjhospital.base;

import android.content.Context;

/**
 * Created by wangxuewei on 2017/10/17.
 */
public abstract class BasePresenter<V,M>{
    public Context mContext;
    public M mModel;
    public V mView;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){

    }
    public void onDestroy() {

    }

}