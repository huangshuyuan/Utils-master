package com.hsy.utils.utilsdemo.module.itemBean;

import java.util.List;

/**
 * 作者：huangshuyuan on 2017/5/26 12:01
 * 邮箱：hshuyuan@foxmail.com
 */

public class ItemBean {

    /**
     * category : ["休息视频","Android","福利","iOS"]
     * error : false
     * results : {"Android":[{"_id":"59212247421aa92c73b64732","createdAt":"2017-05-21T13:14:47.939Z","desc":"Android自定义多种风格进度控件","images":["http://img.gank.io/f7154fed-98dc-46a5-a269-591b974677c4"],"publishedAt":"2017-05-22T11:30:21.8Z","source":"web","type":"Android","url":"https://github.com/WhiteDG/ProgressView","used":true,"who":"Wh1te"},{"_id":"59215c74421aa92c769a8b90","createdAt":"2017-05-21T17:23:00.943Z","desc":"ExoPlayerFilter uses OpenGL Shaders to apply effects on EXOPlayer video at Runtime","images":["http://img.gank.io/3a9d52ca-8956-4f97-b143-df644e67f6b7"],"publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"Android","url":"https://github.com/MasayukiSuda/ExoPlayerFilter","used":true,"who":"Masayuki Suda"},{"_id":"5921836e421aa92c769a8b91","createdAt":"2017-05-21T20:09:18.940Z","desc":"From Java To Kotlin - Your Cheat Sheet For Java To Kotlin Edit","images":["http://img.gank.io/b885fe82-64fd-4765-9aa1-917bfe27d9ba"],"publishedAt":"2017-05-22T11:30:21.8Z","source":"web","type":"Android","url":"https://github.com/MindorksOpenSource/from-java-to-kotlin","used":true,"who":"AMIT SHEKHAR"},{"_id":"5921997a421aa92c7be61b2e","createdAt":"2017-05-21T21:43:22.877Z","desc":"内存优化","publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/ab4a7e353076","used":true,"who":"heming.chen"},{"_id":"5921c4a5421aa92c7be61b30","createdAt":"2017-05-22T00:47:33.319Z","desc":"做好 App 的新手指引，能让其功能第一次与用户见面快速让用户上手，本文整理了 Github 上一些不错的新手指引开源控件，希望对开发者们有所帮助。","images":["http://img.gank.io/59fbc106-14c6-4696-98ca-069d4c55bc74"],"publishedAt":"2017-05-22T11:30:21.8Z","source":"web","type":"Android","url":"http://blog.coderclock.com/2017/05/22/android/open-source-android-app-guide-view-library/","used":true,"who":"D_clock"},{"_id":"59225752421aa92c73b64734","createdAt":"2017-05-22T11:13:22.777Z","desc":"谷歌官方 Android Instant App Sample。","publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"Android","url":"https://github.com/googlesamples/android-instant-apps","used":true,"who":"Allen"},{"_id":"5922577f421aa92c769a8b9b","createdAt":"2017-05-22T11:14:07.248Z","desc":"Android Emoji 兼容包，彻底解决 Android Emoji 不一致问题。","images":["http://img.gank.io/352b6dd1-9aae-4350-b0c3-ba3459590c25"],"publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"Android","url":"https://github.com/googlesamples/android-EmojiCompat","used":true,"who":"Allen"},{"_id":"592257ca421aa92c7be61b32","createdAt":"2017-05-22T11:15:22.486Z","desc":"Loading 状态的闪烁效果，类似 Facebook 的加载，做的很漂亮。","images":["http://img.gank.io/86d33a83-f353-4db2-b92e-6781f8e1755e"],"publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"Android","url":"https://github.com/team-supercharge/ShimmerLayout","used":true,"who":"Allen"}],"iOS":[{"_id":"59225817421aa92c7be61b33","createdAt":"2017-05-22T11:16:39.950Z","desc":"随机键盘的 Passcode 效果，全力保证数据安全。","images":["http://img.gank.io/56e07d99-38d6-4522-a1e6-5cfe508a59fb"],"publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"iOS","url":"https://github.com/cruisediary/Passcode","used":true,"who":"S"},{"_id":"5922588b421aa92c7be61b34","createdAt":"2017-05-22T11:18:35.510Z","desc":"又一个漂亮的下载进度条效果。","publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"iOS","url":"https://github.com/LeonardoCardoso/NFDownloadButton","used":true,"who":"S"}],"休息视频":[{"_id":"592042d5421aa92c7be61b2a","createdAt":"2017-05-20T21:21:25.301Z","desc":"【文曰小强】7分钟速读刘慈欣2003 年银河奖作品《地球大炮》（ 《带上她的眼睛》）","publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10671910/","used":true,"who":"LHF"}],"福利":[{"_id":"59223657421aa92c794632f4","createdAt":"2017-05-22T08:52:39.188Z","desc":"5-22","publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fftusiwb8hj20u00zan1j.jpg","used":true,"who":"代码家"}]}
     */

    private boolean error;
    private DailyList results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public DailyList getResults() {
        return results;
    }

    public void setResults(DailyList results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }


}
