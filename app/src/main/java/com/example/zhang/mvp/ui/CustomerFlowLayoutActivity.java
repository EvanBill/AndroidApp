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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author zzh
 */
public class CustomerFlowLayoutActivity extends BaseActivity<CustomerFlowLayoutPresenter> implements CustomerFlowLayoutContract.ICustomerFlowLayoutView {
    @BindView(R.id.tv_customer_flow_layout_content)
    TextView tvCustomerFlowLayoutContent;
    @BindView(R.id.vs_customer_layout)
    ViewStub vsCustomerLayout;
    private LinearLayout llCustomerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_flow_layout);
        ButterKnife.bind(this);
        presenter = new CustomerFlowLayoutPresenter(this);
    }

    @OnClick({R.id.btn_customer_flow_layout_click})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_customer_flow_layout_click) {
            presenter.getContent();
            if (vsCustomerLayout.getParent() != null) {
                LinearLayout parentView = (LinearLayout) vsCustomerLayout.inflate();
                llCustomerLayout = parentView.findViewById(R.id.ll_customer_layout);
            } else {
                llCustomerLayout.setVisibility(llCustomerLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        }
    }

    @Override
    public void setContent(String content) {
        tvCustomerFlowLayoutContent.setText(content);
    }
}
