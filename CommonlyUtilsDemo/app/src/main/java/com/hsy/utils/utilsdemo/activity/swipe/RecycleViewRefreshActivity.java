package com.hsy.utils.utilsdemo.activity.swipe;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilslibrary.recylerview.decoration.AdvanceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * RecyclerView+SwpieRefreshLayout实现下拉刷新效果:
 */
public class RecycleViewRefreshActivity extends AppCompatActivity {

    @BindView(R.id.demo_recycler)
    RecyclerView recylerview;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefreshLayout;
    @BindView(R.id.top_bar_img_back)
    ImageView topBarImgBack;
    @BindView(R.id.top_bar_tv_back)
    TextView topBarTvBack;
    @BindView(R.id.top_bar_linear_back)
    LinearLayout topBarLinearBack;
    @BindView(R.id.top_bar_title)
    TextView topBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去除系统标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_refresh);
        ButterKnife.bind(this);

        initView();
    }

    /*RecyclerView 管理器*/
    private LinearLayoutManager linearLayoutManager;
    MyRecyclerViewAdapter adapter;
    private int lastVisibleItem;//记录滚动位置


    /**
     * 初始化组件
     */
    private void initView() {
        topBarTitle.setText("RecyclerView 刷新");

        //设置刷新时动画的颜色，可以设置4个
        swiperefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swiperefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swiperefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));


        //设置竖直方向
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置管理器
        recylerview.setLayoutManager(linearLayoutManager);
        //添加分隔线
        recylerview.addItemDecoration(new AdvanceDecoration(this, OrientationHelper.VERTICAL));

        recylerview.setAdapter(adapter = new MyRecyclerViewAdapter(this));
        /*实现下拉*/

        swiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> newDatas = new ArrayList<String>();
                        for (int i = 0; i < 5; i++) {
                            int index = i + 1;
                            newDatas.add("new item" + index);
                        }
                        adapter.addItem(newDatas);/*添加数据*/
                        swiperefreshLayout.setRefreshing(false);
                        Toast.makeText(RecycleViewRefreshActivity.this, "更新了五条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });

        /**
         * 添加滚动监听，实现上拉刷新
         */

        recylerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //当滚动到底部时刷新数据
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> newDatas = new ArrayList<String>();
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                newDatas.add("more item" + index);
                            }
                            adapter.addMoreItem(newDatas);
                            swiperefreshLayout.setRefreshing(false);

                        }
                    }, 1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取滚动的最后位置
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    /**
     * RecyclerView的适配器
     */

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {//自定义viewHoder
        List<String> datas;
        Context context;
        /*加载更多*/
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER = 1;

        public MyRecyclerViewAdapter(Context context) {
            this.context = context;

            /*初始化数据*/
            this.datas = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                int index = i + 1;
                datas.add("item" + index);
            }
            /*addItem(datas);*/
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View view) {
                super(view);
            }
        }


        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ItemViewHolder extends ViewHolder {
            public TextView item_tv;

            public ItemViewHolder(View view) {
                super(view);
                item_tv = (TextView) view.findViewById(R.id.text);
            }
        }

        /**
         * 底部view
         */

        class FooterViewHolder extends ViewHolder {

            public FooterViewHolder(View view) {
                super(view);
            }

        }

        /**
         * 绑定布局文件
         *
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_FOOTER) {
                final View view = LayoutInflater.from(context).inflate(R.layout.refresh_footview_layout, parent, false);
//                view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
//                        RecyclerView.LayoutParams.WRAP_CONTENT));
                FooterViewHolder viewHolder = new FooterViewHolder(view);
                return viewHolder;

            } else if (viewType == TYPE_ITEM) {

                final View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_layout, parent, false);
                //这边可以做一些属性设置，甚至事件监听绑定
                //view.setBackgroundColor(Color.RED);
                ItemViewHolder viewHolder = new ItemViewHolder(view);
                return viewHolder;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder instanceof ItemViewHolder) {
                //设置数据
                ((ItemViewHolder) holder).item_tv.setText(datas.get(position));
                ((ItemViewHolder) holder).item_tv.setTag(position);
            }

        }

        /*返回每一项的类型*/
        @Override
        public int getItemViewType(int position) {
            // 最后一个item设置为footerView
            if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return TYPE_ITEM;
            }
        }

        // RecyclerView的count设置为数据总条数+ 1（footerView）
        @Override
        public int getItemCount() {
            return datas.size() + 1;
        }

        //添加数据

        public void addItem(List<String> newDatas) {
            //mTitles.add(position, data);
            //notifyItemInserted(position);
            newDatas.addAll(datas);
            datas.removeAll(datas);
            datas.addAll(newDatas);
            notifyDataSetChanged();
        }

        /**
         * 添加更多数据
         *
         * @param newDatas
         */

        public void addMoreItem(List<String> newDatas) {
            datas.addAll(newDatas);
            adapter.notifyDataSetChanged();
        }
    }
}
