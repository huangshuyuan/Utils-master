package com.hsy.utils.utilsdemo.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilslibrary.activity.BaseActivity;

public class MainActivity extends BaseActivity {
//    MultiTypeAdapter d;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_scrolling;
    }

    FloatingActionButton fab;
    Toolbar toolbar;
    Button button, button2;

    @Override
    protected void setUpView() {
        toolbar = (Toolbar) $(R.id.toolbar);
        fab = (FloatingActionButton) $(R.id.fab);
        button = (Button) $(R.id.button1);
        button2 = (Button) $(R.id.button2);

    }

    @Override
    protected void setUpData() {
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                show("你好呀");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityWithoutExtras(PagerActivity.class);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithoutExtras(Main2Actibity.class);
            }
        });
        $(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithoutExtras(FullscreenActivity.class);
            }
        });
        $(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithoutExtras(LoginActivity.class);
            }
        });
        $(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithoutExtras(LeftActivity.class);
            }
        });
        $(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithoutExtras(SwipeActivity.class);
            }
        });
    }
}
