package com.example.zhang.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhang.GlideApp;
import com.example.zhang.R;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;

import java.util.List;

public class SmartRefreshAdapter extends BaseQuickAdapter<SmartRefreshBean, BaseViewHolder> {
    public SmartRefreshAdapter(@Nullable List data) {
        super(R.layout.item_smart_refresh, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SmartRefreshBean item) {
        GlideApp.with(mContext).load(item.getBgPicture()).into((ImageView) helper.getView(R.id.iv_item_smart_refresh));
        helper.setText(R.id.tv_item_smart_refresh, item.getName());
    }
}
