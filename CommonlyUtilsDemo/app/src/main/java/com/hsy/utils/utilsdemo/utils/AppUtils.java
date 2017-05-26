package com.hsy.utils.utilsdemo.utils;

import android.content.Context;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hsy.utils.utilslibrary.SettingCenter;

/**
 * 作者：huangshuyuan on 2017/5/26 17:04
 * 邮箱：hshuyuan@foxmail.com
 */

public class AppUtils {

    public static void clearCache(Context context, final SettingCenter.ClearCacheListener listener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title("温馨提示")
                .content("确定清空缓存吗？如果你使用的是移动网络不建议清空缓存哦")
                .positiveText("确定").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        SettingCenter.clearAppCache(listener);
                    }
                }).negativeText("取消");

        MaterialDialog dialog = builder.build();
        dialog.show();
    }
}
