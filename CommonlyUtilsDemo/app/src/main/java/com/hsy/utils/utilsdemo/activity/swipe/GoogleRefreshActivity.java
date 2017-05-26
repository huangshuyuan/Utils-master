package com.hsy.utils.utilsdemo.activity.swipe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.hsy.utils.utilsdemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Google原生的SwipeRefreshLayout---滚动监听上拉刷新*/
public class GoogleRefreshActivity extends AppCompatActivity implements AbsListView.OnScrollListener {


    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lv;
    private ArrayAdapter adapter;
    private List<String> list;
    private View footerView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x101:
                    if (swipeRefreshLayout.isRefreshing()) {
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);//设置不刷新
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_refresh);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_srl);
        lv = (ListView) findViewById(R.id.main_lv);

        /**
         * 对listview添加底部的组件实现上拉
         */
        footerView = getLayoutInflater().inflate(R.layout.refresh_footview_layout, null);
        lv.addFooterView(footerView);
        /**
         * 设置滚动监听
         */
        lv.setOnScrollListener(this);
        list = new ArrayList<>();
        list.addAll(Arrays.asList("Java", "php", "C++", "C#", "IOS", "html", "C", "J2ee", "j2se", "VB", ".net", "Http", "tcp", "udp", "www"));
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, list);

        lv.setAdapter(adapter);
        /*加载的渐变动画*/
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*刷新加载数据*/
                new LoadDataThread().start();
            }
        });
    }

    private int visibleLastIndex;//用来可显示的最后一条数据的索引

    /**
     * 重写滚动方法
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (adapter.getCount() == visibleLastIndex && scrollState == SCROLL_STATE_IDLE) {
            Toast.makeText(this, "加载更多完成", Toast.LENGTH_SHORT).show();
            footerView.setVisibility(View.GONE);
            /*在此处加载更多数据*/
//            new LoadDataThread().start();
        }else {
            footerView.setVisibility(View.VISIBLE);
//            Toast.makeText(this, "加载更多...", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;//减去最后一个加载中那条

    }

    /**
     * 模拟加载数据的线程
     */
    class LoadDataThread extends Thread {
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x101);//通过handler发送一个更新数据的标记
        }

        private void initData() {
            list.addAll(Arrays.asList("Json", "XML", "UDP", "http"));

        }
    }
}

