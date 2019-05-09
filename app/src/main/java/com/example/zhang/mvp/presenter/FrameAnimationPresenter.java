package com.example.zhang.mvp.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.example.zhang.R;
import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.FrameAnimationContract;
import com.example.zhang.mvp.model.FrameAnimationModel;
import com.example.zhang.utils.FrameAnimationUtils;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :
 */
public class FrameAnimationPresenter extends BasePresenter<FrameAnimationContract.IFrameAnimationContractView, FrameAnimationModel> {
    private FrameAnimationUtils.FramesAnimation animation;

    public FrameAnimationPresenter(FrameAnimationContract.IFrameAnimationContractView view) {
        super(view);
        model = new FrameAnimationModel();
    }

    /**
     * 开启动画
     *
     * @param context   上下文
     * @param imageView image组件
     */
    public void startFrameAnimation(Context context, ImageView imageView) {
        animation = FrameAnimationUtils.getInstance().createFramesAnimation(context, imageView, R.array.face_scan_anim, 66, false);
        animation.start();
    }
}
