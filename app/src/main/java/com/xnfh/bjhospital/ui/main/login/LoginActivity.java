package com.xnfh.bjhospital.ui.main.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xnfh.bjhospital.R;
import com.xnfh.bjhospital.base.BaseActivity;
import com.xnfh.bjhospital.utils.SharePrefUtil;
import com.xnfh.bjhospital.utils.ToastUitl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangxuewei on 2017/10/23.
 */
public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_main_title)
    TextView tv_main_title;
    @BindView(R.id.et_login_username)
    EditText et_login_username;
    @BindView(R.id.et_login_password)
    EditText et_login_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.tv_forget)
    TextView tv_forget;



//    @BindView(R.id.iv_main_search)
//    ImageView iv_main_search;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        tv_main_title.setText("登录");
        iv_back.setImageResource(R.mipmap.icon_navbar_return);
    }
    @OnClick({R.id.iv_back,R.id.btn_login})
    public void onClickLis(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                LoginActivity.this.finish();
                break;
            case R.id.btn_login:
                mPresenter.login(et_login_username.getText().toString(),et_login_password.getText().toString());
                break;

        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void loginSuccess(String token) {
        SharePrefUtil.getInstance(this).saveStringData("token",token);
    }

    @Override
    public void loginError(String errorMsg) {
        ToastUitl.showShort(errorMsg);
    }
}
