package com.xnfh.bjhospital.ui.main.find;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xnfh.bjhospital.R;
import com.xnfh.bjhospital.adapter.ViewPagerAdapter;
import com.xnfh.bjhospital.base.BaseFragment;
import com.xnfh.bjhospital.ui.main.currentupdate.CurrentUpdateFragment;
import com.xnfh.bjhospital.ui.main.hot.HotFragment;
import com.xnfh.bjhospital.ui.main.recommend.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wangxuewei on 2017/10/19.
 */
public class FindFragment extends BaseFragment<FindPresenter,FindModel> {
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;

    @BindView(R.id.viewpager_main)
    ViewPager viewpager_main;

    private RecommendFragment recommendFragment;
    private HotFragment hotFragment;
    private CurrentUpdateFragment currentUpdateFragment;


//    private Fragment[] fragments = {new RecommendFragment(),new HotFragment(),new CurrentUpdateFragment()};
    private ViewPagerAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_find;
    }

    @Override
    public void initPresenter() {
        mPresenter = new FindPresenter();
    }

    @Override
    protected void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        recommendFragment = new RecommendFragment();
        hotFragment = new HotFragment();
        currentUpdateFragment = new CurrentUpdateFragment();

        fragmentList.add(recommendFragment);
        fragmentList.add(hotFragment);
        fragmentList.add(currentUpdateFragment);

        adapter = new ViewPagerAdapter(getChildFragmentManager(),fragmentList);
        viewpager_main.setAdapter(adapter);
        tab_layout.setupWithViewPager(viewpager_main);  //关联TabLayout和ViewPager
        tab_layout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
