package com.hsy.utils.utilsdemo.activity.swipe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;


import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.adapter.MyAdapter;
import com.hsy.utils.utilslibrary.view.MySwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 支持自定义上拉刷新界面
 */
public class MySwipeRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        MySwipeRefreshLayout.OnLoadListener {

    private MySwipeRefreshLayout swipeLayout;
    private ListView listView;
    private MyAdapter adapter;
    private ArrayList<HashMap<String, String>> list;
    private View header;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_refresh);
        initView();
        setData();
        setListener();
        /*设置自动刷新 swipeLayout.setRefreshing(true);
		在 setRefreshing(true)并没有触发onRefresh的,须要手动调用一次
        */
//		swipeLayout.post(new Thread(new Runnable() {
//			@Override
//			public void run() {
//				swipeLayout.setRefreshing(true);
//			}
//		}));
//		onRefresh();
        }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.header, null);
        swipeLayout = (MySwipeRefreshLayout) findViewById(R.id.swipe_container);
        /*设置加载进度动画*/
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
    }

    /**
     * 添加数据
     */
    private void setData() {
        list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("itemImage", i + "默认");
            map.put("itemText", i + "默认");
            list.add(map);
        }
        listView = (ListView) findViewById(R.id.list);
        listView.addHeaderView(header);
        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //下拉监听
        swipeLayout.setOnRefreshListener(this);
        //上拉监听
        swipeLayout.setOnLoadListener(this);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 更新数据  更新完后调用该方法结束刷新
                list.clear();
                for (int i = 0; i < 8; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("itemImage", i + "刷新");
                    map.put("itemText", i + "刷新");
                    list.add(map);
                }
                adapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        }, 2000);

    }

    /**
     * 加载更多
     */
    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新数据  更新完后调用该方法结束刷新
                swipeLayout.setLoading(false);
                for (int i = 1; i < 10; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("itemImage", i + "更多");
                    map.put("itemText", i + "更多");
                    list.add(map);
                }
                adapter.notifyDataSetChanged();
            }
        }, 2000);
    }

}



