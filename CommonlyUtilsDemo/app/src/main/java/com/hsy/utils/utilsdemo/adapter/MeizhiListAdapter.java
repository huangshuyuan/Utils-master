/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.hsy.utils.utilsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;
import com.hsy.utils.utilsdemo.module.home.Daily;
import com.hsy.utils.utilslibrary.utils.GlideUtils;

import java.util.List;


/**
 * Created by drakeet on 6/20/15.
 */
public class MeizhiListAdapter
        extends RecyclerView.Adapter<MeizhiListAdapter.ViewHolder> {

    public static final String TAG = "MeizhiListAdapter";

    private List<Daily> mList;
    private Context mContext;
    private OnMeizhiTouchListener mOnMeizhiTouchListener;


    public MeizhiListAdapter(Context context, List<Daily> meizhiList) {
        mList = meizhiList;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meizhi, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Daily meizhi = mList.get(position);
        int limit = 48;
        String text = "";
        if (meizhi != null && meizhi.getTitle() != null) {
            text = meizhi.getTitle().length() > limit ? meizhi.getTitle().substring(0, limit) +
                    "..." : meizhi.getTitle();
        }
        viewHolder.meizhi = meizhi;
        viewHolder.titleView.setText(text);
        viewHolder.card.setTag(meizhi.getDesc());

        GlideUtils.displayImage(mContext, meizhi.getImgUrl(), viewHolder.meizhiView);

    }


    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public void setOnMeizhiTouchListener(OnMeizhiTouchListener onMeizhiTouchListener) {
        this.mOnMeizhiTouchListener = onMeizhiTouchListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView meizhiView;
        TextView titleView;
        View card;
        Daily meizhi;


        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            meizhiView = (ImageView) itemView.findViewById(R.id.meizhi);
            titleView = (TextView) itemView.findViewById(R.id.title);
            meizhiView.setOnClickListener(this);
            card.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mOnMeizhiTouchListener.onTouch(v, meizhiView, card, meizhi);
        }
    }
}
