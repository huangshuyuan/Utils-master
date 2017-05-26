package com.hsy.utils.utilsdemo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.config.API;
import com.hsy.utils.utilsdemo.config.Category;
import com.hsy.utils.utilsdemo.module.home.CategoryList;
import com.hsy.utils.utilsdemo.module.home.CategoryListViewBinder;
import com.hsy.utils.utilsdemo.module.home.Daily;
import com.hsy.utils.utilsdemo.module.home.DailyViewBinder;
import com.hsy.utils.utilsdemo.module.home.DeilyBean;
import com.hsy.utils.utilslibrary.fragment.base.AbsListFragment;
import com.hsy.utils.utilslibrary.fragment.base.BaseFragment;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：huangshuyuan on 2017/5/24 09:38
 * 邮箱：hshuyuan@foxmail.com
 */

public class Fragment2 extends AbsListFragment {

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_2;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUserVisibleHint(true);
    }

    @Override
    protected void setUpView() {
        super.setUpView();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getActivity().getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//        }
        // 初始化请求队列，传入的参数是请求并发值。
        mQueue = NoHttp.newRequestQueue();
    }


    @Override
    public void loadData(final int pageIndex) {
        final List data = new ArrayList();
        if (pageIndex == getInitPageIndex()) {
            CategoryList categoryList = new CategoryList();
            categoryList.setData(Category.getGanHuoCateGory());
            data.add(categoryList);
        }

        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest(API.DEALIY_URL + pageIndex, RequestMethod.GET);
        System.out.println(API.DEALIY_URL + pageIndex);
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
                data.addAll(dataBean.getResults());
                onDataSuccessReceived(pageIndex, data);

            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    /**
     * 请求队列。
     */
    private RequestQueue mQueue;

    /**
     * 回调对象，接受请求结果.
     */
    private static final int NOHTTP_WHAT_TEST = 0x001;


    @Override
    protected int getInitPageIndex() {
        return 1;
    }
}
