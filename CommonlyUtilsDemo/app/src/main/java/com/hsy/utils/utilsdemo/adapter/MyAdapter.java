package com.hsy.utils.utilsdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hsy.utils.utilsdemo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends BaseAdapter {
	public ArrayList<HashMap<String, String>> list;
	public Context context;
	public LayoutInflater layoutInflater;

	public MyAdapter(Context context, ArrayList<HashMap<String, String>> list) {
		this.context = context;
		this.list = list;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		ViewHolder holder = null;
		if (convertView == null) {
			view = layoutInflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.txt  =(TextView) view.findViewById(R.id.textView1);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.txt.setText(list.get(position).get("itemText"));
		return view;
	}

	static class ViewHolder {
		
		TextView txt;
	}

}
