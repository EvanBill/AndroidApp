package com.example.zhang.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.bean.ProductBean;
import com.example.zhang.model.contract.MainContract;
import com.example.zhang.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {
    @BindView(R.id.btn_main_click)
    Button btn_main_click;
    @BindView(R.id.tv_main_content)
    TextView tv_main_content;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);

    }

    @OnClick(R.id.btn_main_click)
    void onClick() {
//        presenter.getData();
        presenter.rxJavaCreateExample();
    }

    @Override
    public void showContent(List<ProductBean> productBeanList) {
        StringBuilder sb = new StringBuilder();
        sb.append("调用次数" + (++count) + "\n");
        for (ProductBean item : productBeanList) {
            sb.append(item.getName() + "\n");
        }
        tv_main_content.setText(sb.toString());
        count = 0;
    }

    @Override
    protected void onDestroy() {
        presenter.onFinishActivity();
        super.onDestroy();
    }
}
