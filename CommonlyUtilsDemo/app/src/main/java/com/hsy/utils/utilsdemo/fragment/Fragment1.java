package com.hsy.utils.utilsdemo.fragment;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.adapter.BannerAdapter;
import com.hsy.utils.utilslibrary.fragment.base.BaseFragment;
import com.hsy.utils.utilslibrary.view.AutoPlayViewPager;
import com.hsy.utils.utilslibrary.viewpager.anim.CubeTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：huangshuyuan on 2017/5/24 09:38
 * 邮箱：hshuyuan@foxmail.com
 */

public class Fragment1 extends BaseFragment implements View.OnClickListener {
    private AutoPlayViewPager autoPlayViewPage;
    List<Integer> resIds;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_1;
    }

    @Override
    protected void setUpView() {
        autoPlayViewPage = (AutoPlayViewPager) $(R.id.autoviewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0之上使用
//            //沉浸式状态栏
//            Window window = getActivity().getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.transport));//透明
//        }
    }

    @Override
    protected void setUpData() {
        listener();
        Data();
        initViewPager();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0之上使用
//            //沉浸式状态栏
//            Window window = getActivity().getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.transport));//透明
//        }
    }

    private void Data() {
        resIds = new ArrayList<>();
        // 模拟几张图片
        resIds.add(R.mipmap.img5);
        resIds.add(R.mipmap.img1);
        resIds.add(R.mipmap.img2);
        resIds.add(R.mipmap.img3);
        resIds.add(R.mipmap.img4);
        resIds.add(R.mipmap.img5);
        resIds.add(R.mipmap.img1);
    }

    private void initViewPager() {
        BannerAdapter bannerAdapter = new BannerAdapter(getActivity());
        bannerAdapter.update(resIds);

        autoPlayViewPage.setAdapter(bannerAdapter);

        autoPlayViewPage.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        autoPlayViewPage.setCurrentItem(200); // 设置每个Item展示的时间
        autoPlayViewPage.start(); // 开始轮播
        // 立方体
        autoPlayViewPage.setPageTransformer(true, new CubeTransformer());
//        autoPlayViewPage.setPageTransformer(true, new StackPageTransformer(autoPlayViewPage, 0.8f, 0.9f, 3));
// *0.8f -> // 栈底: 最小页面缩放比
// *0.9f -> // 栈顶: 最大页面缩放比
// *5    -> // 栈内页面数
    }


    private void listener() {
        $(R.id.btn_scroll_change_left).setOnClickListener(this);
        $(R.id.btn_scroll_change_right).setOnClickListener(this);
        $(R.id.btn_scroll_previous).setOnClickListener(this);
        $(R.id.btn_scroll_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //点击事件
        switch (v.getId()) {
            case R.id.btn_scroll_previous:// 播放上一个
                autoPlayViewPage.previous();
                break;
            case R.id.btn_scroll_next:// 播放下一个
                autoPlayViewPage.next();
                break;
            case R.id.btn_scroll_change_left:// 改变为向左滑动
                autoPlayViewPage.setDirection(AutoPlayViewPager.Direction.LEFT);
                break;
            case R.id.btn_scroll_change_right:// 改变为向右滑动
                autoPlayViewPage.setDirection(AutoPlayViewPager.Direction.RIGHT);
                break;

        }
    }
}
