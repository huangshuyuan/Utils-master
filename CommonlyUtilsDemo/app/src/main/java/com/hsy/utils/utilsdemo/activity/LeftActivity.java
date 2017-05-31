package com.hsy.utils.utilsdemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.utils.AppUtils;
import com.hsy.utils.utilslibrary.SettingCenter;
import com.hsy.utils.utilslibrary.activity.BaseActivity;
import com.hsy.utils.utilslibrary.utils.SpannableStringUtils;
import com.hsy.utils.utilslibrary.utils.ToastUtils;

//缓存
public class LeftActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_left;
    }

    TextView mine_cache_clear;

    @Override
    protected void setUpView() {
        Toolbar toolbar = (Toolbar) $(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) $(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) $(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) $(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mine_cache_clear = (TextView) $(R.id.mine_cache_clear);
        refresh();
        mine_cache_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.clearCache(LeftActivity.this, new SettingCenter.ClearCacheListener() {
                    @Override
                    public void onResult() {
                        ToastUtils.getInstance().showToast(getString(R.string.cache_clear_success));
                        mine_cache_clear.setText(getString(R.string.mine_cache_clear));
                    }
                });
            }
        });
    }

    public void refresh() {
        SettingCenter.countDirSizeTask(new SettingCenter.CountDirSizeListener() {
            @Override
            public void onResult(long result) {
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append(getString(R.string.mine_cache_clear));
                builder.append("\n");
                builder.append(SpannableStringUtils.format(LeftActivity.this, "(" + SettingCenter.formatFileSize(result) + ")", R.style.ByTextAppearance));
                mine_cache_clear.setText(builder);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.left, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
