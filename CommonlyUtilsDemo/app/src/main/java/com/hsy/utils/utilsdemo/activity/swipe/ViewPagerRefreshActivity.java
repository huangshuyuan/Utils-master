package com.hsy.utils.utilsdemo.activity.swipe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;


import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.pager.PageOneView;
import com.hsy.utils.utilsdemo.pager.PageThreeView;
import com.hsy.utils.utilsdemo.pager.PageTwoView;
import com.hsy.utils.utilsdemo.pager.PageView;
import com.hsy.utils.utilslibrary.view.VpSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 支持自定义Viewpage下拉刷新界面,和Google原生用法一致
 */
public class ViewPagerRefreshActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.swiperefresh)
    VpSwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_refresh);
        ButterKnife.bind(this);
        setRefresh();

        /*初始化viewpager*/
        initData();

    }

    /**
     * 设置刷新组件
     * <p>
     * 1、setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener):设置手势滑动监听器。
     * <p>
     * 2、setProgressBackgroundColor(int colorRes):设置进度圈的背景色。
     * <p>
     * 3、setColorSchemeResources(int… colorResIds):设置进度动画的颜色。
     * <p>
     * 4、setRefreshing(Boolean refreshing):设置组件的刷洗状态。
     * <p>
     * 5、setSize(int size):设置进度圈的大小，只有两个值：DEFAULT、LARGE
     * <p>
     *   6、postDelayed(new Runable(),long min) 设置刷新延迟时间
     */

    private void setRefresh() {
        // 设置进度圈的背景色。
        swiperefresh.setProgressBackgroundColorSchemeColor(Color.parseColor("#e6b8af"));
//        设置进度动画的颜色。
        swiperefresh.setColorSchemeResources(R.color.color_w1, R.color.color_w2, R.color.color_w3);
        //设置进度圈的大小
        swiperefresh.setSize(SwipeRefreshLayout.DEFAULT);

        //刷新监听
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //延迟两秒钟
                swiperefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        initData();
                        //设置组件的刷洗状态。
                        swiperefresh.setRefreshing(false);
                    }
                }, 2000);


                ;
            }
        });


    }

    private List<PageView> pageList;//页面数组

    private List<String> titleList;  //标题列表数组

    private void initData() {
        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("Page1");
        titleList.add("Page2");
        titleList.add("Page3");
        titleList.add("Page1");
        titleList.add("Page2");
        titleList.add("Page3");
        pageList = new ArrayList<>();
        pageList.add(new PageOneView(ViewPagerRefreshActivity.this));
        pageList.add(new PageTwoView(ViewPagerRefreshActivity.this));
        pageList.add(new PageThreeView(ViewPagerRefreshActivity.this));
        pageList.add(new PageOneView(ViewPagerRefreshActivity.this));
        pageList.add(new PageTwoView(ViewPagerRefreshActivity.this));
        pageList.add(new PageThreeView(ViewPagerRefreshActivity.this));
        viewpager.setAdapter(new SamplePagerAdapter());


    }

    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageList.get(position));
            return pageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**
         * 标题
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return titleList.get(position);
        }


    }
}
