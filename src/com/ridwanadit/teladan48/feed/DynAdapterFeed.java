package com.ridwanadit.teladan48.feed;

import java.util.ArrayList;

import com.ridwanadit.teladan48.R;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
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
		//Log.d("Tag", list.get(position).getAuthorGravatar().toString());
		holder = new ViewHolder();
		
		if(convertView==null){
			holder.author = new ImageView (context);
			holder.author.setAdjustViewBounds(true);
			holder.author.setMaxHeight(80);
			holder.author.setMaxWidth(80);
			holder.author.setPadding(10,10,10,10);
			holder.author.setId(1);
			RelativeLayout.LayoutParams lpiv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpiv.addRule(RelativeLayout.ALIGN_LEFT);
			lpiv.addRule(RelativeLayout.ALIGN_TOP);
			rl.addView(holder.author,lpiv);

			holder.titleTV = new TextView(context);
			holder.titleTV.setTextSize(18);
			holder.titleTV.setSingleLine(true);
			holder.titleTV.setId(2);
			RelativeLayout.LayoutParams lpTitle = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lpTitle.addRule(RelativeLayout.RIGHT_OF, holder.author.getId());
			rl.addView(holder.titleTV,lpTitle);

			holder.creatorTV = new TextView(context);
			holder.creatorTV.setTextSize(14);
			holder.creatorTV.setId(3);
			RelativeLayout.LayoutParams lpCreator = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpCreator.addRule(RelativeLayout.BELOW, holder.titleTV.getId());
			lpCreator.addRule(RelativeLayout.RIGHT_OF, holder.author.getId());
			rl.addView(holder.creatorTV,lpCreator);

			holder.dateTV= new TextView(context);
			holder.dateTV.setTextSize(12);
			holder.dateTV.setId(4);
			RelativeLayout.LayoutParams lpDate = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpDate.addRule(RelativeLayout.BELOW, holder.creatorTV.getId());
			lpDate.addRule(RelativeLayout.RIGHT_OF, holder.author.getId());
			rl.addView(holder.dateTV,lpDate);

//			holder.excerpt = new WebView(context);
//			holder.excerpt.setPadding(0, 0, 0, 5);
//			holder.excerpt.setBackgroundColor(0x00000000);
//			holder.excerpt.setId(5);
//			RelativeLayout.LayoutParams lpExcerpt = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//			lpExcerpt.addRule(RelativeLayout.BELOW, holder.dateTV.getId());
//			lpExcerpt.addRule(RelativeLayout.RIGHT_OF, holder.author.getId());
//			rl.addView(holder.excerpt,lpExcerpt);
			
			holder.contentTV = new TextView(context);
			holder.contentTV.setTextSize(12);
			holder.contentTV.setPadding(0, 0, 0, 5);
			holder.contentTV.setMaxLines(4);
			holder.contentTV.setId(5);
			RelativeLayout.LayoutParams lpContent = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lpContent.addRule(RelativeLayout.BELOW, holder.dateTV.getId());
			lpContent.addRule(RelativeLayout.RIGHT_OF, holder.author.getId());
			rl.addView(holder.contentTV,lpContent);

			View v = new View(context);
			v.setBackgroundColor(Color.WHITE);
			v.setId(6);
			RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,2);
			lpv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			rl.addView(v,lpv);
			rl.setTag(holder);
			convertView = rl;
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		String Title = Html.fromHtml(lay.getFeedPostTitle()).toString();
		holder.titleTV.setText(limit(Title,25));
		holder.creatorTV.setText(lay.getAuthor());
		holder.dateTV.setText(lay.getDate());
		holder.contentTV.setText(Html.fromHtml(lay.getContent().replace("\\", "")));
		//String excerpt = lay.getContent().replace("\\", "");
		//holder.excerpt.loadData("<html><body>"+excerpt+"</body></html>", "text/html; charset=UTF-8", null);
		
		if (lay.getImage()!=null){
			holder.author.setImageBitmap(lay.getImage());
		} else {
			holder.author.setImageResource(R.drawable.gravatar_default);
		}
		return convertView;
	}

	static class ViewHolder {
		TextView titleTV;
		TextView creatorTV;
		TextView dateTV;
		TextView contentTV;
		WebView excerpt;

		ImageView author;
	}
	
	public static String limit (String str, int length) {
		StringBuilder buf = new StringBuilder(str);
		if (buf.length()>length) {
			buf.setLength(length);
			buf.append("...");
		}
			
		return buf.toString();
	}
}
