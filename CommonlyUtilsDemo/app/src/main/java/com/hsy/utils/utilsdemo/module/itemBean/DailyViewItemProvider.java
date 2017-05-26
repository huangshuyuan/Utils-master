package com.hsy.utils.utilsdemo.module.itemBean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilslibrary.activity.WebViewActivity;
import com.hsy.utils.utilslibrary.utils.DateUtils;
import com.hsy.utils.utilslibrary.utils.SpannableStringUtils;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by _SOLID
 * Date:2016/11/30
 * Time:22:26
 */
public class DailyViewItemProvider
        extends ItemViewProvider<GanHuoData, DailyViewItemProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_daily_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull final ViewHolder holder, @NonNull final GanHuoData recently) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(
                SpannableStringUtils.format(holder.tv_title.getContext(), "[" + DateUtils.friendlyTime(recently.getPublishedAt().replace('T', ' ').replace('Z', ' ')) +
                        "]", R.style.ByTextAppearance));
        builder.append(recently.getDesc());
        builder.append(
                SpannableStringUtils.format(holder.tv_title.getContext(), " [via " +
                        recently.getWho() + "]", R.style.ByTextAppearance));
        holder.tv_title.setText(builder.subSequence(0, builder.length()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开网页工具类
                WebViewActivity.start(holder.itemView.getContext(), recently.getDesc(), recently.getUrl());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                DialogUtils.showActionDialog(v.getContext(), v
//                        , new CollectTable(recently.getDesc(), recently.getUrl(), recently.getType()));
                return true;
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}