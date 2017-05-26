package com.hsy.utils.utilsdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.hsy.utils.utilsdemo.config.API;
import com.hsy.utils.utilsdemo.module.home.DeilyBean;
import com.hsy.utils.utilsdemo.module.itemBean.DailyList;
import com.hsy.utils.utilsdemo.module.itemBean.DailyTitle;
import com.hsy.utils.utilsdemo.module.itemBean.GanHuoData;
import com.hsy.utils.utilsdemo.module.itemBean.ItemBean;
import com.hsy.utils.utilslibrary.fragment.base.AbsListFragment;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 作者：huangshuyuan on 2017/5/26 11:01
 * 邮箱：hshuyuan@foxmail.com
 */

public class RecentlyListFragment extends AbsListFragment {
    private String date;

    public static RecentlyListFragment newInstance(int year, int month, int day) {
        RecentlyListFragment fragment = new RecentlyListFragment();
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        args.putInt("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = getArguments().getInt("year") + "/"
                + getArguments().getInt("month") + "/"
                + getArguments().getInt("day");
        mQueue = NoHttp.newRequestQueue();
    }

    @Override
    protected void customConfig() {
        disAbleLoadMore();
        disAbleRefresh();
    }

    @Override
    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(getItems()) {
            @NonNull
            @Override
            public Class onFlattenClass(@NonNull Object item) {
                if (item instanceof GanHuoData) {
                    return GanHuoData.DailyItem.class;
                }
                return super.onFlattenClass(item);
            }
        };
    }

    /**
     * 请求队列。
     */
    private RequestQueue mQueue;

    /**
     * 回调对象，接受请求结果.
     */
    private static final int NOHTTP_WHAT_TEST = 0x002;

    @Override
    @SuppressWarnings("unchecked")
    public void loadData(final int pageIndex) {
        if (TextUtils.isEmpty(date)) {
            showError(new Exception("date is null"));
            return;
        }
        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest(API.DEALIY_GANHUO_URL + date, RequestMethod.GET);
        System.out.println(API.DEALIY_URL + date);
        mQueue.add(NOHTTP_WHAT_TEST, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                Gson gson = new Gson();
                ItemBean itemBean = gson.fromJson(result, ItemBean.class);
                DailyList recentlyBean = itemBean.getResults();
                List list = new ArrayList<>();

                if (recentlyBean != null) {
                    if (recentlyBean.get休息视频() != null) {
                        list.add(new DailyTitle("休息视频"));
                        list.addAll(recentlyBean.get休息视频());
                    }
                    if (recentlyBean.getAndroid() != null) {
                        list.add(new DailyTitle("Android"));
                        list.addAll(recentlyBean.getAndroid());
                    }
                    if (recentlyBean.getIOS() != null) {
                        list.add(new DailyTitle("iOS"));
                        list.addAll(recentlyBean.getIOS());
                    }
                    if (recentlyBean.get前端() != null) {
                        list.add(new DailyTitle("前端"));
                        list.addAll(recentlyBean.get前端());
                    }
                    if (recentlyBean.getApp() != null) {
                        list.add(new DailyTitle("App"));
                        list.addAll(recentlyBean.getApp());
                    }
                    if (recentlyBean.get瞎推荐() != null) {
                        list.add(new DailyTitle("瞎推荐"));
                        list.addAll(recentlyBean.get瞎推荐());
                    }


                }
                onDataSuccessReceived(pageIndex, list);

            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    @NonNull
    @Override
    protected String getEmptyMsg() {
        return "今日暂无干货";
    }
}
