package com.hsy.utils.utilsdemo.fragment;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.utils.AppUtils;
import com.hsy.utils.utilslibrary.SettingCenter;
import com.hsy.utils.utilslibrary.fragment.base.BaseFragment;
import com.hsy.utils.utilslibrary.utils.SpannableStringUtils;
import com.hsy.utils.utilslibrary.utils.ToastUtils;

/**
 * 作者：huangshuyuan on 2017/5/24 09:38
 * 邮箱：hshuyuan@foxmail.com
 */

public class Fragment3 extends BaseFragment {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_3;
    }

    TextView mine_cache_clear;

    @Override
    protected void setUpView() {
        mine_cache_clear = (TextView) $(R.id.mine_cache_clear);
    }

    @Override
    protected void setUpData() {
        refresh();
        mine_cache_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.clearCache(getContext(), new SettingCenter.ClearCacheListener() {
                    @Override
                    public void onResult() {
                        ToastUtils.getInstance().showToast(getString(R.string.cache_clear_success));
                        mine_cache_clear.setText(getString(R.string.mine_cache_clear));
                    }
                });
            }
        });
    }

    @Override
    public void refresh() {
        SettingCenter.countDirSizeTask(new SettingCenter.CountDirSizeListener() {
            @Override
            public void onResult(long result) {
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append(getString(R.string.mine_cache_clear));
                builder.append("\n");
                builder.append(SpannableStringUtils.format(getContext(), "(" + SettingCenter.formatFileSize(result) + ")", R.style.ByTextAppearance));
                mine_cache_clear.setText(builder);
            }
        });
    }
}
