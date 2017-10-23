package com.xnfh.bjhospital.ui.main.login;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.Request;
import com.xnfh.bjhospital.base.BasePresenter;
import com.xnfh.bjhospital.utils.AccountValidatorUtil;
import com.xnfh.bjhospital.utils.DESUtils;
import com.xnfh.bjhospital.utils.ToastUitl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangxuewei on 2017/10/23.
 */
public class LoginPresenter extends BasePresenter<LoginView,LoginModel> {
    public void login(String username,String password) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            ToastUitl.showShort("用户名或密码不能为空");
            return;
        }
        if(!AccountValidatorUtil.isMobile(username)) {
            ToastUitl.showShort("请输入正确手机号");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("account=").append(username).append("&").append("password=").append(password);
        String encrypt = DESUtils.encrypt(sb.toString());

        OkGo.<String>post("http://42.51.166.251:5946/api/v1/MembersLogin.ashx").upString(encrypt).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body();
                String status = "";
                String info = "";
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    status = jsonObject.get("status").toString();
                    info = jsonObject.get("info").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if("success".equals(status)) {
                    mView.loginSuccess(info);
                }else {
                    mView.loginError(info);
                }
                mView.stopLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                mView.showLoading("正在登录");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                mView.stopLoading();
                ToastUitl.showShort("登录失败");
            }
        });

    }
}
