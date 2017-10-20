package com.xnfh.bjhospital.ui.main.recommend;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xnfh.bjhospital.base.BasePresenter;
import com.xnfh.bjhospital.bean.FindBean;
import com.xnfh.bjhospital.http.Config;
import com.xnfh.bjhospital.utils.DESUtils;
import com.xnfh.bjhospital.utils.JsonUitl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by wangxuewei on 2017/10/19.
 */
public class RecommendPresenter extends BasePresenter<RecommendView,RecommendModel> {
    String para = "type=3&Mold=1&Pagesize=10&Pageindex=1";
    String decode = DESUtils.encrypt(para);
    public void getMessage() {
        OkGo.<String>post(Config.baseUrl + Config.Mothod.find).upString(decode).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body();

                FindBean findBean =JsonUitl.mGson.fromJson(json,FindBean.class);

                if(findBean.getStatus().equals(findBean.getStatus())) {
                    List<FindBean.InfoBean.RowsBean> rows = findBean.getInfo().getRows();
                    mView.success(rows);
                }else {
                    mView.error();
                }
                mView.stopLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                String error = response.body();
                mView.error();
                mView.stopLoading();
            }
        });
    }
}
