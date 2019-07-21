package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
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
import com.example.zhang.utils.IntentUtils;
import com.example.zhang.utils.LogUtils;

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
    @BindView(R.id.tb_activity_top)
    Toolbar tb_activity_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(tb_activity_top);
        getSupportActionBar().setTitle(R.string.app_name);
        presenter = new MainPresenter(this);
//        StringLogUtils.Companion.logString();
        showFragment(0);
    }


    @Override
    public void showContent(List<ProductBean> productBeanList) {

    }

    @OnClick({R.id.ll_activity_video_list, R.id.ll_activity_image_list,
            R.id.ll_activity_tools, R.id.ll_activity_setting})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_activity_video_list:
                showFragment(0);
                break;
            case R.id.ll_activity_image_list:
                showFragment(1);
                break;
            case R.id.ll_activity_tools:
                showFragment(2);
                break;
            case R.id.ll_activity_setting:
                showFragment(3);
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

    public void showFragment(int position) {
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
                return;
            }
            ft.show(fragment).hide(currentFragment);
        }
        currentFragment = fragment;
        ft.commitAllowingStateLoss();
    }
}
