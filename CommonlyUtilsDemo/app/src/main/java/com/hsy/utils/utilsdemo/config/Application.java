package com.hsy.utils.utilsdemo.config;

import com.hsy.utils.utilslibrary.SolidApplication;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;

/**
 * 作者：huangshuyuan on 2017/5/23 17:57
 * 邮箱：hshuyuan@foxmail.com
 */

public class Application extends SolidApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this, new NoHttp.Config()
                .setConnectTimeout(15 * 1000) // 全局连接超时时间，单位毫秒。
                .setReadTimeout(15 * 1000) // 全局服务器响应超时时间，单位毫秒。
                .setNetworkExecutor(new OkHttpNetworkExecutor()) // 使用OkHttp做网络层。

        );
        NoHttp.initialize(this, new NoHttp.Config()
                .setCacheStore(
                        new DBCacheStore(this) // 配置缓存到数据库。
                                .setEnable(true) // true启用缓存，fasle禁用缓存。
                )
        );
        Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("NoHttpSample"); // 设置NoHttp打印Log的TAG。

        //配置adpter
        MultiTypeInstaller.install();
    }
}
