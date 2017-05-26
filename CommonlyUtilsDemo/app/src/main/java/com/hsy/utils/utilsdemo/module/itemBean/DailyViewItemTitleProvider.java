package com.hsy.utils.utilsdemo.module.itemBean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by _SOLID
 * Date:2016/11/30
 * Time:22:20
 */
public class DailyViewItemTitleProvider
        extends ItemViewProvider<DailyTitle, DailyViewItemTitleProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_daily_item_title, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull ViewHolder holder, @NonNull DailyTitle recentlyTitle) {
        holder.tv_title.setText(recentlyTitle.getTitle());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;

        ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}