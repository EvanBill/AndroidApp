package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.CustomerFlowLayoutContract;
import com.example.zhang.mvp.presenter.CustomerFlowLayoutPresenter;
import com.example.zhang.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CustomerFlowLayoutActivity extends BaseActivity<CustomerFlowLayoutPresenter> implements CustomerFlowLayoutContract.ICustomerFlowLayoutView {
    @BindView(R.id.tv_customer_flow_layout_content)
    TextView tv_customer_flow_layout_content;
    @BindView(R.id.vs_customer_layout)
    ViewStub vs_customer_layout;
    private LinearLayout ll_customer_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_flow_layout);
        ButterKnife.bind(this);
        presenter = new CustomerFlowLayoutPresenter(this);
    }

    @OnClick({R.id.btn_customer_flow_layout_click})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_customer_flow_layout_click:
                presenter.getContent();
                if (vs_customer_layout.getParent() != null) {
                    LinearLayout parentView = (LinearLayout) vs_customer_layout.inflate();
                    ll_customer_layout = parentView.findViewById(R.id.ll_customer_layout);
                } else {
                    ll_customer_layout.setVisibility(ll_customer_layout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void setContent(String content) {
        tv_customer_flow_layout_content.setText(content);
    }
}
