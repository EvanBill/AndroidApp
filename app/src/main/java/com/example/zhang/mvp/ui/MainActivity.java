package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.MainContract;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.mvp.presenter.MainPresenter;
import com.example.zhang.mvp.ui.fragment.ImageListFragment;
import com.example.zhang.mvp.ui.fragment.SettingFragment;
import com.example.zhang.mvp.ui.fragment.ToolsFragment;
import com.example.zhang.mvp.ui.fragment.VideoListFragment;
import com.example.zhang.utils.LogUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author zzh
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    private long backTime = 0;
    private Fragment currentFragment;
    @BindView(R.id.iv_main_navigation_video)
    ImageView iv_main_navigation_video;
    @BindView(R.id.iv_main_navigation_image)
    ImageView iv_main_navigation_image;
    @BindView(R.id.iv_main_navigation_tools)
    ImageView iv_main_navigation_tools;
    @BindView(R.id.iv_main_navigation_setting)
    ImageView iv_main_navigation_setting;
    @BindView(R.id.tv_main_navigation_video)
    TextView tv_main_navigation_video;
    @BindView(R.id.tv_main_navigation_image)
    TextView tv_main_navigation_image;
    @BindView(R.id.tv_main_navigation_tools)
    TextView tv_main_navigation_tools;
    @BindView(R.id.tv_main_navigation_setting)
    TextView tv_main_navigation_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.app_name);
        presenter = new MainPresenter(this);
//        StringLogUtils.Companion.logString();
        showFragment(0);
        setNavigationSelect(0);
        setGoogleAnalysic();
    }

    private void setGoogleAnalysic() {
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "111");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name11");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void showContent(List<ProductBean> productBeanList) {

    }

    @OnClick({R.id.ll_activity_video_list, R.id.ll_activity_image_list,
            R.id.ll_activity_tools, R.id.ll_activity_setting})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_activity_video_list:
                if (showFragment(0)) {
                    setNavigationSelect(0);
                }
                break;
            case R.id.ll_activity_image_list:
                if (showFragment(1)) {
                    setNavigationSelect(1);
                }
                break;
            case R.id.ll_activity_tools:
                if (showFragment(2)) {
                    setNavigationSelect(2);
                }
                break;
            case R.id.ll_activity_setting:
                if (showFragment(3)) {
                    setNavigationSelect(3);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        int backTimeInterval = 2;
        if (TimeUtils.getTimeSpan(TimeUtils.getNowMills(), backTime, TimeConstants.SEC) > backTimeInterval) {
            backTime = TimeUtils.getNowMills();
            ToastUtils.showShort(getString(R.string.str_back_press_info));
        } else {
            super.onBackPressed();
        }
    }

    public boolean showFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = fm.findFragmentByTag("videoListFragment");
                if (fragment == null) {
                    fragment = new VideoListFragment();
                    ft.add(R.id.ll_main_content, fragment, "videoListFragment");
                    LogUtils.error("lll", "videoListFragment 新建");
                }
                break;
            case 1:
                fragment = fm.findFragmentByTag("imageListFragment");
                if (fragment == null) {
                    fragment = new ImageListFragment();
                    ft.add(R.id.ll_main_content, fragment, "imageListFragment");
                    LogUtils.error("lll", "imageListFragment 新建");
                }
                break;
            case 2:
                fragment = fm.findFragmentByTag("toolsFragment");
                if (fragment == null) {
                    fragment = new ToolsFragment();
                    ft.add(R.id.ll_main_content, fragment, "toolsFragment");
                    LogUtils.error("lll", "toolsFragment 新建");
                }
                break;
            case 3:
                fragment = fm.findFragmentByTag("settingFragment");
                if (fragment == null) {
                    fragment = new SettingFragment();
                    ft.add(R.id.ll_main_content, fragment, "settingFragment");
                    LogUtils.error("lll", "settingFragment 新建");
                }
                break;
            default:
                break;
        }


        if (currentFragment != null) {
            if (fragment == currentFragment) {
                return false;
            }
            ft.show(fragment).hide(currentFragment);
        }
        currentFragment = fragment;
        ft.commitAllowingStateLoss();
        return true;
    }

    /**
     * 设置底部导航栏
     *
     * @param position
     */
    private void setNavigationSelect(int position) {
        switch (position) {
            case 0:
                iv_main_navigation_video.setSelected(true);
                iv_main_navigation_image.setSelected(false);
                iv_main_navigation_tools.setSelected(false);
                iv_main_navigation_setting.setSelected(false);
                tv_main_navigation_video.setTextColor(ContextCompat.getColor(this, R.color.color_theme));
                tv_main_navigation_image.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_tools.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_setting.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                break;
            case 1:
                iv_main_navigation_video.setSelected(false);
                iv_main_navigation_image.setSelected(true);
                iv_main_navigation_tools.setSelected(false);
                iv_main_navigation_setting.setSelected(false);
                tv_main_navigation_video.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_image.setTextColor(ContextCompat.getColor(this, R.color.color_theme));
                tv_main_navigation_tools.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_setting.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                break;
            case 2:
                iv_main_navigation_video.setSelected(false);
                iv_main_navigation_image.setSelected(false);
                iv_main_navigation_tools.setSelected(true);
                iv_main_navigation_setting.setSelected(false);
                tv_main_navigation_video.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_image.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_tools.setTextColor(ContextCompat.getColor(this, R.color.color_theme));
                tv_main_navigation_setting.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                break;
            case 3:
                iv_main_navigation_video.setSelected(false);
                iv_main_navigation_image.setSelected(false);
                iv_main_navigation_tools.setSelected(false);
                iv_main_navigation_setting.setSelected(true);
                tv_main_navigation_video.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_image.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_tools.setTextColor(ContextCompat.getColor(this, R.color.color_navigation_text));
                tv_main_navigation_setting.setTextColor(ContextCompat.getColor(this, R.color.color_theme));
                break;
            default:
                break;
        }
    }
}
