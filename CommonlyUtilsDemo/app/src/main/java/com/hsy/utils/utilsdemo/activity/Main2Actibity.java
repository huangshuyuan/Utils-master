package com.hsy.utils.utilsdemo.activity;

import android.support.annotation.NonNull;

import com.hsy.utils.utilslibrary.activity.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.fragment.Fragment1;
import com.hsy.utils.utilsdemo.fragment.Fragment2;
import com.hsy.utils.utilsdemo.fragment.Fragment3;
import com.hsy.utils.utilslibrary.activity.BaseActivity;
import com.hsy.utils.utilslibrary.utils.ViewUtils;

/**
 * 作者：huangshuyuan on 2017/5/26 11:43
 * 邮箱：hshuyuan@foxmail.com
 */

public class Main2Actibity extends com.hsy.utils.utilslibrary.activity.BaseActivity {

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;//记录当前的Fragment
    BottomNavigationView navigation;
    private AppBarLayout mAppBarLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_buttom;
    }

    @Override
    protected void setUpView() {
        mAppBarLayout = $(R.id.appbar_layout);
        navigation = (BottomNavigationView) $(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragmentManager = getSupportFragmentManager();
        hideAppBar();
        initDefaultFragment();
    }


    //init the default checked fragment
    private void initDefaultFragment() {

        mCurrentFragment = ViewUtils.createFragment(Fragment1.class);
        mFragmentManager.beginTransaction().add(R.id.content, mCurrentFragment).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hideAppBar();
                    switchFragment(Fragment1.class);
                    return true;
                case R.id.navigation_dashboard:
                    showAppBar();
                    hideAppBar();
                    switchFragment(Fragment2.class);
                    return true;
                case R.id.navigation_notifications:
                    hideAppBar();
                    switchFragment(Fragment3.class);
                    return true;
            }
            return false;
        }

    };

    //切换Fragment
    private void switchFragment(Class<?> clazz) {
        Fragment to = ViewUtils.createFragment(clazz);
        if (to.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.content, to).commitAllowingStateLoss();
        }
        mCurrentFragment = to;
    }

    private void hideAppBar() {
        ViewGroup.LayoutParams layoutParams = mAppBarLayout.getLayoutParams();
        if (layoutParams.height == 0) return;
        layoutParams.height = 0;
        mAppBarLayout.setLayoutParams(layoutParams);
    }

    private void showAppBar() {
        ViewGroup.LayoutParams layoutParams = mAppBarLayout.getLayoutParams();
        if (layoutParams.height != 0) return;
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.app_bar_toolbar_height);
        mAppBarLayout.setLayoutParams(layoutParams);
    }
}
