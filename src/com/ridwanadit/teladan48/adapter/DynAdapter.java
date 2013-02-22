package com.ridwanadit.teladan48.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DynAdapter extends BaseAdapter{
	private ArrayList<DynLayout> list = new ArrayList<DynLayout>();
	@SuppressWarnings("unused")
	private Context context;
	
	public DynAdapter (Context context){
		this.context=context;
	}

	public void additem(DynLayout item) {
		list.add(item);
	}
	
	public void additem(DynLayout item, int pos) {
		list.add(pos,item);
	}
	
	public void removeitem(int pos){
		list.remove(pos);
	}
	
	public void clearlist(){
		list.clear();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		v=convertView;
		return v;
	}
	
}
