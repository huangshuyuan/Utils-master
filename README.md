# UtilsDemo
开发工具类封装
对开发常用的工具类就行封装整理

![](https://github.com/huangshuyuan/UtilsDemo/blob/master/1.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/2.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/3.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/4.png)
![](https://github.com/huangshuyuan/UtilsDemo/blob/master/5.png)

## Gradle：
```
compile 'com.hsy.utils:utilslibrary:1.0.1'
```
##  Maven :
```
<dependency>
  <groupId>com.hsy.utils</groupId>
  <artifactId>utilslibrary</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

# 包含以下：

## 1、基类的封装使用
BaseActivity

BaseFragment

AbsListFragment

...


## 2、工具类封装
BitMapUtils

GlideUtils 图片加载框架

ClipboardUtils 剪切板

DateUtils 日期工具类

DisplayUtils窗口工具类（宽、高等等）

EmptyUtils 判断空工具类

NetworkUtils 网络连接

SnackBarUtils

StringUtils  包括MD5

SystemShareUtils 系统分享工具类

SystemUtils 与系统有关的工具类

ViewUtils 创建Fragment等等

WebViewUtil webview




## 3、样式封装

## 4、RecyclerView的全新使用（不需要每次都创建Adapter），


参考：

https://github.com/drakeet/MultiType

https://drakeet.me/


## 5、viewpager切换动画封装

 * 立体方效果

 CubeTransformer 
 

 * 卡片活动效果

 DepthPageTransformer
 
 * 扇形旋转效果

 RotateDownPageTransformer
 
 * 堆成栈效果
 
 StackPageTransformer
 
 * 向内效果
 
 ZoomOutPageTransformer

## 6、swipeRefreshLayout各种使用（封装）

## 7、缓存清理

## 8、对话框（https://github.com/afollestad/material-dialogs）

## 9、Activity  ＭＤ风格切换动画

## 10、流布局使用、上拉过程中加载数据(demo中)


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
