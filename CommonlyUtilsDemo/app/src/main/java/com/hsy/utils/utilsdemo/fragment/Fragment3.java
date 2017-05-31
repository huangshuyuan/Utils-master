package com.hsy.utils.utilsdemo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.activity.DailyActivity;
import com.hsy.utils.utilsdemo.adapter.MeizhiListAdapter;
import com.hsy.utils.utilsdemo.adapter.OnMeizhiTouchListener;
import com.hsy.utils.utilsdemo.config.API;
import com.hsy.utils.utilsdemo.config.Category;
import com.hsy.utils.utilsdemo.module.home.CategoryList;
import com.hsy.utils.utilsdemo.module.home.Daily;
import com.hsy.utils.utilsdemo.module.home.DeilyBean;
import com.hsy.utils.utilsdemo.utils.AppUtils;
import com.hsy.utils.utilslibrary.SettingCenter;
import com.hsy.utils.utilslibrary.fragment.base.AbsListFragment;
import com.hsy.utils.utilslibrary.fragment.base.BaseFragment;
import com.hsy.utils.utilslibrary.utils.SpannableStringUtils;
import com.hsy.utils.utilslibrary.utils.ToastUtils;
import com.hsy.utils.utilslibrary.view.MultiSwipeRefreshLayout;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者：huangshuyuan on 2017/5/24 09:38
 * 邮箱：hshuyuan@foxmail.com
 * 实现滑动自动加载数据
 */

public class Fragment3 extends BaseFragment implements SwipeRefreshLayer {
    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_3;
    }


    RecyclerView mRecyclerView;
    public MultiSwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRequestDataRefresh = false;

    FloatingActionButton floatingActionButton;

    /**
     * 初始化界面
     */
    @Override
    protected void setUpView() {

        mRecyclerView = (RecyclerView) $(R.id.list);
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) $(R.id.swipe_refresh_layout);
        floatingActionButton = (FloatingActionButton) $(R.id.main_fab);

    }

    /**
     * 初始化数据
     */

    @Override
    protected void setUpData() {
        mMeizhiList = new ArrayList<>();

        // 初始化请求队列，传入的参数是请求并发值。
        mQueue = NoHttp.newRequestQueue();
        //初始化swiperefresh，
        trySetupSwipeRefresh();
        //初始化Recyclerview
        setupRecyclerView();

    }


    /**
     * 请求队列。
     */
    private RequestQueue mQueue;

    /**
     * 回调对象，接受请求结果.
     */
    private static final int NOHTTP_WHAT_TEST = 0x002;


    private MeizhiListAdapter mMeizhiListAdapter;
    private List<Daily> mMeizhiList;
    private boolean mIsFirstTimeTouchBottom = true;
    private int mPage = 1;
    private boolean mMeizhiBeTouched;

    private void setupRecyclerView() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mMeizhiListAdapter = new MeizhiListAdapter(getContext(), mMeizhiList);
        mRecyclerView.setAdapter(mMeizhiListAdapter);
        mRecyclerView.addOnScrollListener(getOnBottomListener(layoutManager));//滚动预先加载数据
        mMeizhiListAdapter.setOnMeizhiTouchListener(getOnMeizhiTouchListener());//点击事件
    }

    private static final int PRELOAD_SIZE = 6;

    //滚动监听
    RecyclerView.OnScrollListener getOnBottomListener(final StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom =
                        layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >=
                                mMeizhiListAdapter.getItemCount() - PRELOAD_SIZE;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom) {
                    if (!mIsFirstTimeTouchBottom) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        mPage += 1;
                        loadData();
                    } else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    private void loadData() {
        //上拉加载不需要清除数据
        loadData(false);/* clean */
    }

    private int mLastVideoIndex = 0;

    /**
     * 获取服务数据
     *
     * @param clean 清除来自数据库缓存或者已有数据。
     */
    private void loadData(final boolean clean) {
        mLastVideoIndex = 0;

        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest(API.DEALIY_URL + mPage, RequestMethod.GET);
        System.out.println(API.DEALIY_URL + mPage);
        if (mQueue != null) {
            mQueue.add(NOHTTP_WHAT_TEST, request, new OnResponseListener<String>() {
                @Override
                public void onStart(int what) {
                }

                @Override
                public void onSucceed(int what, Response<String> response) {
                    String result = response.get();
                    Gson gson = new Gson();
                    DeilyBean dataBean = gson.fromJson(result, DeilyBean.class);
//            System.out.println(result);

                    if (clean) mMeizhiList.clear();
                    mMeizhiList.addAll(dataBean.getResults());
                    mMeizhiListAdapter.notifyDataSetChanged();
                    setRefresh(false);
                }

                @Override
                public void onFailed(int what, Response<String> response) {
                    loadError(response.get());
                }

                @Override
                public void onFinish(int what) {
                    setRefresh(false);
                }
            });
        } else {
            loadError("网络不好");
        }
    }

    private void loadError(String throwable) {
        Snackbar.make(mRecyclerView, "数据加载失败", Snackbar.LENGTH_LONG)
                .setAction("再试一次", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestDataRefresh();
                    }
                })
                .show();
    }


    private OnMeizhiTouchListener getOnMeizhiTouchListener() {
        return new OnMeizhiTouchListener() {
            @Override
            public void onTouch(View v, View meizhiView, View card, Daily daily) {
                if (daily == null) return;
                if (v == meizhiView && !mMeizhiBeTouched) {
                    //点击图片
                    mMeizhiBeTouched = true;
                    DailyActivity.start((Activity) v.getContext(), v, daily.getTitle(), daily.getDate(), daily.getImgUrl());
                } else if (v == card) {
                    //点击view
                    DailyActivity.start((Activity) v.getContext(), v, daily.getTitle(), daily.getDate(), daily.getImgUrl());
                } else {
                    DailyActivity.start((Activity) v.getContext(), v, daily.getTitle(), daily.getDate(), daily.getImgUrl());
                }
            }
        };
    }


    void trySetupSwipeRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                    R.color.refresh_progress_2, R.color.refresh_progress_1);
            // Do not use lambda here!
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //下拉刷新
                    requestDataRefresh();
                }
            });

            requestDataRefresh();//首次加载
        }
        //置顶
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public void requestDataRefresh() {
        mIsRequestDataRefresh = true;
        mPage = 1;
        loadData(true);

    }

    @Override
    public void setRefresh(boolean requestDataRefresh) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            // 防止刷新消失太快，让子弹飞一会儿.
            mSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 1000);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void setProgressViewOffset(boolean scale, int start, int end) {
        mSwipeRefreshLayout.setProgressViewOffset(scale, start, end);
    }

    @Override
    public void setCanChildScrollUpCallback(MultiSwipeRefreshLayout.CanChildScrollUpCallback callback) {
        mSwipeRefreshLayout.setCanChildScrollUpCallback(callback);
    }
}
