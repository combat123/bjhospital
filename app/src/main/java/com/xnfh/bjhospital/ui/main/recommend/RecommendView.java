package com.xnfh.bjhospital.ui.main.recommend;

import com.xnfh.bjhospital.base.BaseView;
import com.xnfh.bjhospital.bean.FindBean;

import java.util.List;

/**
 * Created by wangxuewei on 2017/10/19.
 */
interface RecommendView extends BaseView {
    @Override
    public void showLoading(String title);

    @Override
    public void stopLoading();

    @Override
    public void showErrorTip(String msg);

    void success(List<FindBean.InfoBean.RowsBean> rows);
    void error();
}
