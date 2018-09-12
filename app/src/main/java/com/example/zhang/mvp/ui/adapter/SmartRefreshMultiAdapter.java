package com.example.zhang.mvp.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhang.GlideApp;
import com.example.zhang.R;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;

import java.util.List;

public class SmartRefreshMultiAdapter extends BaseMultiItemQuickAdapter<SmartRefreshBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SmartRefreshMultiAdapter(List<SmartRefreshBean> data) {
        super(data);
        addItemType(1, R.layout.item_smart_refresh);
        addItemType(2, R.layout.item_smart_refresh_2);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmartRefreshBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_item_smart_refresh, item.getName());
                GlideApp.with(mContext).load(item.getBgPicture()).into((ImageView) helper.getView(R.id.iv_item_smart_refresh));
                break;
            case 2:
                helper.setText(R.id.tv_item_smart_refresh_2, item.getName());
                break;
        }
    }
}
