# UtilsDemo
开发工具类封装
对开发常用的工具类就行封装整理

![](https://github.com/huangshuyuan/UtilsDemo/blob/master/1.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/2.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/3.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/4.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/5.png)

# 包含以下：

1、基类的封装使用

2、工具类封装

3、样式封装

4、RecyclerView的全新使用（不需要每次都创建Adapter），


参考：

https://github.com/drakeet/MultiType

https://drakeet.me/


5、viewpager切换动画封装

6、swipeRefreshLayout各种使用（封装）

7、缓存清理

8、对话框（https://github.com/afollestad/material-dialogs）

9、Activity  ＭＤ风格切换动画

10、流布局使用、上拉过程中加载数据


```

    compile 'com.android.support:design:25.3.1'
    //VIewpager 切换需要
    /compile files('libs/nineoldandroids-2.4.0.jar')


    //RecyclerView Adapter
    compile('me.drakeet.multitype:multitype:2.4.3', {
        exclude group: 'com.android.support'
    })
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'me.solidev.library:status-view-layout:0.2.2'
    /*图片工具类*/
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'jp.wasabeef:glide-transformations:2.0.2'
    // If you want to use the GPU Filters
    compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1'
    // If you want to use the GPU Filters
    /*图片工具类*/
/*https://github.com/wasabeef/glide-transformations*/
对话框
    compile 'com.afollestad.material-dialogs:core:0.8.5.9'
```

## 参考：

https://github.com/burgessjp/GanHuoIO

https://github.com/burgessjp/MaterialDesignDemo
