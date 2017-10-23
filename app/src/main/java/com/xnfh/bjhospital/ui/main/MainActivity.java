package com.xnfh.bjhospital.ui.main;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xnfh.bjhospital.R;
import com.xnfh.bjhospital.base.BaseActivity;
import com.xnfh.bjhospital.ui.main.Classify.ClassifyFragment;
import com.xnfh.bjhospital.ui.main.find.FindFragment;
import com.xnfh.bjhospital.ui.main.login.LoginActivity;
import com.xnfh.bjhospital.utils.SharePrefUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_find)
    RadioButton btn_find;
    @BindView(R.id.btn_classify)
    RadioButton btn_classify;
    @BindView(R.id.btn_mine)
    RadioButton btn_mine;

    //标题栏
    @BindView(R.id.tv_main_title)
    TextView tv_main_title;
    @BindView(R.id.iv_main_search)
    ImageView iv_main_search;
    @BindView(R.id.iv_main_ask)
    ImageView iv_main_ask;

    private FragmentManager supportFragmentManager;

    private FindFragment findFragment;
    private ClassifyFragment classifyFragment;



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        supportFragmentManager = getSupportFragmentManager();
        findFragment = new FindFragment();
        classifyFragment = new ClassifyFragment();

        tv_main_title.setText("发现");
        iv_main_search.setImageResource(R.mipmap.select);
        supportFragmentManager.beginTransaction().add(R.id.fl_body,findFragment).commit();
        supportFragmentManager.beginTransaction().add(R.id.fl_body,classifyFragment).commit();
        supportFragmentManager.beginTransaction().hide(classifyFragment).show(findFragment).commit();

    }
    @OnClick({R.id.btn_find,R.id.btn_classify,R.id.btn_mine,R.id.iv_main_search,R.id.iv_main_ask})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find:     //发现页面
                tv_main_title.setText("发现");

                supportFragmentManager.beginTransaction().hide(classifyFragment).show(findFragment).commit();

                break;
            case R.id.btn_classify:     //分类页面
                tv_main_title.setText("分类");


                supportFragmentManager.beginTransaction().hide(findFragment).show(classifyFragment).commit();

                break;
            case R.id.btn_mine:     //我的页面
                String session = SharePrefUtil.getInstance(this).getStringData("session");
                if("".equals(session)) {
                    startActivity(new Intent(this,LoginActivity.class));
                }
                break;
            case R.id.iv_main_search:       //标题栏搜索按钮

                break;
            case R.id.iv_main_ask:
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https:m.baidu.com"));
                startActivity(intent);
                break;
        }
    }
}
