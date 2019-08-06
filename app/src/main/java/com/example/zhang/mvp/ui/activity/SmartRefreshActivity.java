package com.example.zhang.mvp.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.SmartRefreshContract;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;
import com.example.zhang.mvp.presenter.SmartRefreshPresenter;
import com.example.zhang.mvp.ui.adapter.SmartRefreshMultiAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zzh
 */
public class SmartRefreshActivity extends BaseActivity<SmartRefreshPresenter> implements SmartRefreshContract.ISmartRefreshView {
    @BindView(R.id.srl_smart_refresh)
    SmartRefreshLayout srlSmartRefresh;
    @BindView(R.id.rv_smart_refresh)
    RecyclerView rvSmartRefresh;
    // private SmartRefreshAdapter adapter;

    private SmartRefreshMultiAdapter adapter;
    private List<SmartRefreshBean> smartRefreshBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh);
        ButterKnife.bind(this);
        presenter = new SmartRefreshPresenter(this);
        srlSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData();
            }
        });
        srlSmartRefresh.setEnableLoadMore(false);
//        adapter = new SmartRefreshAdapter(smartRefreshBeanList);
        adapter = new SmartRefreshMultiAdapter(smartRefreshBeanList);
        rvSmartRefresh.setLayoutManager(new LinearLayoutManager(this));
        rvSmartRefresh.setItemAnimator(new DefaultItemAnimator());
        rvSmartRefresh.setHasFixedSize(true);
        rvSmartRefresh.setAdapter(adapter);
        srlSmartRefresh.autoRefresh();
    }

    @Override
    public void showContent(List<SmartRefreshBean> smartRefreshBeans) {
        srlSmartRefresh.finishRefresh();
        srlSmartRefresh.finishLoadMore();
        smartRefreshBeanList.clear();
        smartRefreshBeanList.addAll(smartRefreshBeans);
        adapter.notifyDataSetChanged();
    }
}
