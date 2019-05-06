package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.GlideContract;
import com.example.zhang.mvp.model.GlideModel;

/**
 * @author zzh
 */
public class GlidePresenter extends BasePresenter<GlideContract.IGlideView, GlideModel> {
    public GlidePresenter(GlideContract.IGlideView view) {
        super(view);
        model = new GlideModel();
    }
}
