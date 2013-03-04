package com.ridwanadit.teladan48.feed;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class DynAdapterFeed extends BaseAdapter{
	private ArrayList<DynLayoutFeed> list = new ArrayList<DynLayoutFeed>();
	
	private Context context;
	
	public DynAdapterFeed (Context context, ArrayList<DynLayoutFeed> list) {
		this.context=context;
		this.list = list;
	}

	public void additem(DynLayoutFeed item) {
		list.add(item);
	}
	
	public void additem(DynLayoutFeed item, int pos) {
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
	public DynLayoutFeed getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		RelativeLayout rl = new RelativeLayout(context);
		DynLayoutFeed lay =  list.get(position);
		Log.d("Tag", list.get(position).getFeedPostID().toString());
			holder = new ViewHolder();
			holder = new ViewHolder();
			holder.titleTV = new TextView(context);
			holder.titleTV.setTextSize(18);
			holder.titleTV.setPadding(15, 7, 15, 7);
			holder.titleTV.setId(1);
			RelativeLayout.LayoutParams lpTitle = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rl.addView(holder.titleTV,lpTitle);
			
			holder.creatorTV = new TextView(context);
			holder.creatorTV.setTextSize(14);
			holder.creatorTV.setPadding(15, 2, 15, 2);
			holder.creatorTV.setId(2);
			RelativeLayout.LayoutParams lpCreator = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpCreator.addRule(RelativeLayout.BELOW, holder.titleTV.getId());
			rl.addView(holder.creatorTV,lpCreator);
			
			holder.dateTV= new TextView(context);
			holder.dateTV.setTextSize(12);
			holder.dateTV.setPadding(15, 2, 15, 2);
			holder.dateTV.setId(3);
			RelativeLayout.LayoutParams lpDate = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpDate.addRule(RelativeLayout.BELOW, holder.creatorTV.getId());
			rl.addView(holder.dateTV,lpDate);
			
			holder.contentTV = new TextView(context);
			holder.contentTV.setTextSize(12);
			holder.contentTV.setPadding(15, 10, 15, 10);
			holder.contentTV.setId(4);
			RelativeLayout.LayoutParams lpContent = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpContent.addRule(RelativeLayout.BELOW, holder.dateTV.getId());
			rl.addView(holder.contentTV,lpContent);
			
//			holder.excerpt = new WebView(context);
//			holder.excerpt.setPadding(15, 10, 15, 10);
//			holder.excerpt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//			holder.excerpt.setId(4);
//			RelativeLayout.LayoutParams lpExcerpt = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//			lpExcerpt.addRule(RelativeLayout.BELOW, holder.dateTV.getId());
//			rl.addView(holder.excerpt,lpExcerpt);
			
			holder.iv = new ImageView (context);
			holder.iv.setAdjustViewBounds(true);
			holder.iv.setMaxHeight(200);
			holder.iv.setMaxWidth(200);
			holder.iv.setPadding(0, 10, 0, 10);
			holder.iv.setId(5);
			RelativeLayout.LayoutParams lpiv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpiv.addRule(RelativeLayout.BELOW, holder.contentTV.getId());
			//lpiv.addRule(RelativeLayout.BELOW, holder.excerpt.getId());
			lpiv.addRule(RelativeLayout.CENTER_HORIZONTAL);
			rl.addView(holder.iv,lpiv);
			
			View v = new View(context);
			v.setBackgroundColor(android.graphics.Color.WHITE);
			v.setId(6);
			RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,2);
			lpv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			rl.addView(v,lpv);
	
			holder.titleTV.setText(lay.getFeedPostTitle());
			holder.creatorTV.setText(lay.getAuthor());
			holder.dateTV.setText(lay.getDate());
			//holder.contentTV.setText(lay.getContent());
			holder.contentTV.setText("Filler");
			rl.setTag(holder);
			convertView = rl;

		return convertView;
	}
	
	static class ViewHolder {
		TextView titleTV;
		TextView creatorTV;
		TextView dateTV;
		TextView contentTV;
		WebView excerpt;

		ImageView iv;
	}
}
