package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.MainContract;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.mvp.presenter.MainPresenter;

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
//        presenter.rxJavaCreateExample();
//        presenter.rxJavaMapExample();
//        presenter.rxJavaZipExample();
//        presenter.rxJavaConcatExample();
//        presenter.rxJavaFlatMapExample();
//        presenter.rxJavaConcatMapExample();
//        presenter.rxJavaDistinctExample();
//        presenter.rxJavaFilterExample();
//        presenter.rxJavaBufferExample();
//        presenter.rxJavaTimeExample();
//        presenter.rxJavaIntervalExample();
//        presenter.rxJavaDoOnNextExample();
//        presenter.rxJavaSkipExample();
//        presenter.rxJavaTakeExample();
//        presenter.rxJavaSingleExample();
//        presenter.rxJavaDebounceExample();
//        presenter.rxJavaDeferExample();
//        presenter.rxJavaLastExample();
//        presenter.rxJavaMergeExample();
//        presenter.rxJavaReduceExample();
//        presenter.rxJavaScanExample();
//        presenter.rxJavaWindowExample();
//        presenter.rxJavaRangeExample();
//        presenter.rxJavaRepeatExample();
//        presenter.rxJavaSchedulersExample();
//        presenter.rxJavaFlowableCreateExample();
//        presenter.rxJavaFlowableSizeExample();
//        presenter.rxJavaFlowableRealExample();
//        presenter.rxJavaFlowableConsumeExample();
        presenter.getMainData();
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
