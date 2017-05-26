package com.hsy.utils.utilsdemo.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;


/**
 * Created by huangshuyuan on 2017/3/10.
 * email:hshuuyuan@foxmail.com
 * version:v1.0
 */

public class PageThreeView extends PageView {
    public PageThreeView(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.page_content, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Page three");
        addView(view);
    }
}
