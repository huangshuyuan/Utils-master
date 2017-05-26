package com.hsy.utils.utilsdemo.config;

/**
 * 作者：huangshuyuan on 2017/5/25 15:45
 * 邮箱：hshuyuan@foxmail.com
 */

public class API {
    public static String BASE_URL = "http://www.gank.io/api/";
    //http://www.gank.io/api/history/content/10/1
    public static String DEALIY_URL = BASE_URL + "history/content/10/";//最后加上页码

    /**
     * 获取某天的干货
     *
     * @param date
     * @return
     */
    public static String DEALIY_GANHUO_URL = BASE_URL + "day/";//最后加上日期


}
