package com.hsy.utils.utilsdemo.config;

import com.hsy.utils.utilsdemo.module.home.CategoryList;
import com.hsy.utils.utilsdemo.module.home.CategoryListViewBinder;
import com.hsy.utils.utilsdemo.module.home.Daily;
import com.hsy.utils.utilsdemo.module.home.DailyViewBinder;
import com.hsy.utils.utilsdemo.module.itemBean.DailyTitle;
import com.hsy.utils.utilsdemo.module.itemBean.DailyViewItemProvider;
import com.hsy.utils.utilsdemo.module.itemBean.DailyViewItemTitleProvider;
import com.hsy.utils.utilsdemo.module.itemBean.GanHuoData;

import me.drakeet.multitype.GlobalMultiTypePool;

/**
 * Created by _SOLID
 * Date:2016/12/1
 * Time:13:06
 * Desc:
 */

public class MultiTypeInstaller {
    static void install() {
        GlobalMultiTypePool.register(CategoryList.class, new CategoryListViewBinder());
        GlobalMultiTypePool.register(Daily.class,  new DailyViewBinder());
        GlobalMultiTypePool.register(DailyTitle.class, new DailyViewItemTitleProvider());
        GlobalMultiTypePool.register(GanHuoData.DailyItem.class, new DailyViewItemProvider());
    }
}
