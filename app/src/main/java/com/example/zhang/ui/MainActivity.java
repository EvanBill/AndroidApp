package com.example.zhang.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        presenter.getData();
    }

    @OnClick(R.id.btn_main_click)
    void onClick() {
        Toast.makeText(this, "fdasdfa", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showContent(List<ProductBean> productBeanList) {
        showToast(productBeanList.get(0).getName());
    }


}
