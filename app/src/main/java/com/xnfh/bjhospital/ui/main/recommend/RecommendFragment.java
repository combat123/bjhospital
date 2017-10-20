package com.xnfh.bjhospital.ui.main.recommend;

import android.widget.ListView;
import android.widget.Toast;

import com.xnfh.bjhospital.R;
import com.xnfh.bjhospital.adapter.ListViewAdapter;
import com.xnfh.bjhospital.base.BaseFragment;
import com.xnfh.bjhospital.bean.FindBean;
import com.xnfh.bjhospital.utils.ToastUitl;
import com.xnfh.bjhospital.weight.PullRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wangxuewei on 2017/10/19.
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter,RecommendModel> implements RecommendView {

    @BindView(R.id.prl_recommend)
    PullRefreshLayout prl_recommend;
    @BindView(R.id.lv_recommend)
    ListView lv_recommend;

    private ListViewAdapter adapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mPresenter.getMessage();
        prl_recommend.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getMessage();
            }
        });
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {
        prl_recommend.setRefreshing(false);
    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void success(List<FindBean.InfoBean.RowsBean> rows) {
        adapter = new ListViewAdapter(getContext(),rows);
        lv_recommend.setAdapter(adapter);
    }

    @Override
    public void error() {
        ToastUitl.showShort("数据错误");
    }
}
