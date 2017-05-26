package com.hsy.utils.utilsdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.activity.swipe.GoogleRefreshActivity;
import com.hsy.utils.utilsdemo.activity.swipe.MySwipeRefreshActivity;
import com.hsy.utils.utilsdemo.activity.swipe.RecycleViewRefreshActivity;
import com.hsy.utils.utilsdemo.activity.swipe.ViewPagerRefreshActivity;
import com.hsy.utils.utilslibrary.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwipeActivity extends BaseActivity {


    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_swipe;
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
    }



    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                /*Google原生的SwipeRefreshLayout---滚动监听上拉刷新*/
                startActivity(new Intent(SwipeActivity.this, GoogleRefreshActivity.class));
                break;
            case R.id.button2:
                /**
                 * 支持自定义上拉刷新界面
                 */
                startActivity(new Intent(SwipeActivity.this, MySwipeRefreshActivity.class));
                break;
            case R.id.button3:
                /**
                 * 支持自定义Viewpage下拉刷新界面
                 */
                startActivity(new Intent(SwipeActivity.this, ViewPagerRefreshActivity.class));
                break;
            case R.id.button4:
                /**
                 * RecyclerView+SwpieRefreshLayout实现下拉刷新
                 */
                startActivity(new Intent(SwipeActivity.this, RecycleViewRefreshActivity.class));
//                Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
