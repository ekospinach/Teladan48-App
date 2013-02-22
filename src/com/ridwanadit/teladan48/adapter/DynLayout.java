package com.ridwanadit.teladan48.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DynLayout extends LinearLayout {
	TextView tv;
	ImageView iv;
	 
	public DynLayout(Context context) {
		super(context);
		setOrientation(VERTICAL);
	}

	public void setInfo(Context context, String name, Bitmap bmp){
		RelativeLayout rl = new RelativeLayout(context);
		addView(rl);
		
		iv = new ImageView(context);
		iv.setId(3);
		iv.setImageBitmap(bmp);
		iv.setPadding(15, 7, 0, 7);
		RelativeLayout.LayoutParams lpiv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpiv.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lpiv.addRule(RelativeLayout.CENTER_VERTICAL);
		rl.addView(iv,lpiv);
		
		tv = new TextView(context);
		tv.setTextSize(14);
		tv.setPadding(15, 7, 0, 7);
		tv.setText(name);
		tv.setId(1);
		RelativeLayout.LayoutParams lptv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lptv.addRule(RelativeLayout.RIGHT_OF, iv.getId());
		rl.addView(tv,lptv);
		
		View v = new View(context);
		v.setBackgroundColor(android.graphics.Color.WHITE);
		v.setId(2);
		RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,2);
		lpv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		rl.addView(v,lpv);
		
	}
	
}
