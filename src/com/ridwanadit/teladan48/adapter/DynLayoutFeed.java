package com.ridwanadit.teladan48.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class DynLayoutFeed extends LinearLayout {
	TextView titleTv, creatorTv, dateTv, contentTv;
	ImageView iv;
	int id;
	WebView wv;

	public DynLayoutFeed(Context context) {
		super(context);
		setOrientation(VERTICAL);
		setId(id);
	}

	public void setId (int i) {
		this.id = i;
	}
	
	public int getId(){
		return id;
	}
	
	public void setInfo(Context context, int id, String title, String creator, String date, String content, Bitmap bm, String link){
		RelativeLayout rl = new RelativeLayout(context);
		this.id = id;
		addView(rl);
		final String url = link;
		
		titleTv = new TextView(context);
		titleTv.setTextSize(18);
		titleTv.setPadding(15, 7, 15, 7);
		titleTv.setText(title);
		titleTv.setId(1);
		RelativeLayout.LayoutParams lpTitle = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		rl.addView(titleTv,lpTitle);
		
		creatorTv = new TextView(context);
		creatorTv.setTextSize(14);
		creatorTv.setPadding(15, 2, 15, 2);
		creatorTv.setText(creator);
		creatorTv.setId(2);
		RelativeLayout.LayoutParams lpCreator = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpCreator.addRule(RelativeLayout.BELOW, titleTv.getId());
		rl.addView(creatorTv,lpCreator);
		
		dateTv = new TextView(context);
		dateTv.setTextSize(12);
		dateTv.setPadding(15, 2, 15, 2);
		dateTv.setText(date);
		dateTv.setId(3);
		RelativeLayout.LayoutParams lpDate = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpDate.addRule(RelativeLayout.BELOW, creatorTv.getId());
		rl.addView(dateTv,lpDate);
		
		contentTv = new TextView(context);
		contentTv.setTextSize(12);
		contentTv.setPadding(15, 10, 15, 10);
		contentTv.setText(content);
		contentTv.setId(4);
		RelativeLayout.LayoutParams lpContent = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpContent.addRule(RelativeLayout.BELOW, dateTv.getId());
		rl.addView(contentTv,lpContent);
		
		iv = new ImageView (context);
		iv.setAdjustViewBounds(true);
		iv.setMaxHeight(200);
		iv.setMaxWidth(200);
		iv.setPadding(0, 10, 0, 10);
		iv.setImageBitmap(bm);
		iv.setId(5);
		RelativeLayout.LayoutParams lpiv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpiv.addRule(RelativeLayout.BELOW, contentTv.getId());
		lpiv.addRule(RelativeLayout.CENTER_HORIZONTAL);
		rl.addView(iv,lpiv);
		
		View v = new View(context);
		v.setBackgroundColor(android.graphics.Color.WHITE);
		v.setId(6);
		RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,2);
		lpv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		rl.addView(v,lpv);
		
		//wv = new WebView(context);
		//wv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

	}
	
}
