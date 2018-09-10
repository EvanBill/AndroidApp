package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.SmartRefreshContract;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;
import com.example.zhang.mvp.presenter.SmartRefreshPresenter;
import com.example.zhang.mvp.ui.adapter.SmartRefreshAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmartRefreshActivity extends BaseActivity<SmartRefreshPresenter> implements SmartRefreshContract.ISmartRefreshView {
    @BindView(R.id.srl_smart_refresh)
    SmartRefreshLayout srl_smart_refresh;
    @BindView(R.id.rv_smart_refresh)
    RecyclerView rv_smart_refresh;
    private SmartRefreshAdapter adapter;
    private List<SmartRefreshBean> smartRefreshBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh);
        ButterKnife.bind(this);
        presenter = new SmartRefreshPresenter(this);
        srl_smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData();
            }
        });
        srl_smart_refresh.setEnableLoadMore(false);
        adapter = new SmartRefreshAdapter(smartRefreshBeanList);
        rv_smart_refresh.setLayoutManager(new LinearLayoutManager(this));
        rv_smart_refresh.setItemAnimator(new DefaultItemAnimator());
        rv_smart_refresh.setHasFixedSize(true);
        rv_smart_refresh.setAdapter(adapter);
        srl_smart_refresh.autoRefresh();
    }

    @Override
    public void showContent(List<SmartRefreshBean> smartRefreshBeans) {
        srl_smart_refresh.finishRefresh();
        srl_smart_refresh.finishLoadMore();
        smartRefreshBeanList.clear();
        smartRefreshBeanList.addAll(smartRefreshBeans);
        adapter.notifyDataSetChanged();
    }
}
