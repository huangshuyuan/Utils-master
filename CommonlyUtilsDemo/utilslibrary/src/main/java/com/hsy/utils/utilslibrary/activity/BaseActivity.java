package com.hsy.utils.utilslibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hsy.utils.utilslibrary.utils.ToastUtils;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(setLayoutResourceID());
        setUpView();
        setUpData();
    }

    protected void setUpData() {
        /**
         * 初始化
         */
        ToastUtils.init(this);
    }


    protected abstract int setLayoutResourceID();

    /***
     * 用于在初始化View之前做一些事
     */
    protected void init() {

    }

    /**
     * 初始化界面
     */
    protected abstract void setUpView();

    /**
     * 绑定数据
     *
     * @param id
     * @param <T>
     * @return
     */

    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }


    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * 显示
     *
     * @param text
     */
    public void show(String text) {
        ToastUtils.getInstance().showToast(text);
    }


}
