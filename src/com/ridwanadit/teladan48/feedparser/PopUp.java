package com.ridwanadit.teladan48.feedparser;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.ridwanadit.teladan48.feed.DynAdapterFeed;

public class PopUp {
	public PopUp(){
		
	}
	
	public void ShowPop(Context context, View parent, DynAdapterFeed Adp, int pos) {
		String header = "<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"></head><body bgcolor=\"black\"><font color=\"white\">";
		String footer = "</font></body></html>";
		String data = header+Adp.getItem(pos).getContent().replace("\\", "")+footer;
		final PopupWindow popup =  new PopupWindow(context);
		popup.setHeight(600);
		popup.setWidth(420);
		
		RelativeLayout relLay = new RelativeLayout(context);
		relLay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		relLay.setBackgroundColor(Color.BLACK);
		
		TextView close = new TextView(context);
		close.setTextSize(10);
		close.setText("Close");
		close.setId(7);
		close.setPadding(0, 3, 3, 0);
		close.setGravity(Gravity.RIGHT);
		close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popup.dismiss();
			}
		});
		RelativeLayout.LayoutParams lpclose = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		lpclose.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lpclose.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		relLay.addView(close, lpclose);
		
		ImageView authorImage = new ImageView(context);
		authorImage.setAdjustViewBounds(true);
		authorImage.setMaxHeight(80);
		authorImage.setMaxWidth(80);
		authorImage.setPadding(5,5,5,0);
		authorImage.setId(1);
		authorImage.setImageBitmap(Adp.getItem(pos).getImage());
		RelativeLayout.LayoutParams lpiv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpiv.addRule(RelativeLayout.BELOW, close.getId());
		relLay.addView(authorImage, lpiv);
		
		TextView authorName = new TextView(context);
		authorName.setTextSize(16);
		authorName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		authorName.setText(Adp.getItem(pos).getAuthor());
		authorName.setId(2);
		RelativeLayout.LayoutParams lpauthor = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpauthor.addRule(RelativeLayout.RIGHT_OF, authorImage.getId());
		lpauthor.addRule(RelativeLayout.BELOW, close.getId());
		relLay.addView(authorName, lpauthor);
		
		TextView feedTime = new TextView(context);
		feedTime.setTextSize(12);
		feedTime.setId(3);		
		feedTime.setText(Adp.getItem(pos).getDate());
		RelativeLayout.LayoutParams lptime = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lptime.addRule(RelativeLayout.RIGHT_OF, authorImage.getId());
		lptime.addRule(RelativeLayout.BELOW, authorName.getId());
		relLay.addView(feedTime, lptime);		

		TextView feedTitle = new TextView(context);
		feedTitle.setTextSize(14);
		feedTitle.setTypeface(null,Typeface.BOLD);
		feedTitle.setText(Html.fromHtml(Adp.getItem(pos).getFeedPostTitle()));
		feedTitle.setId(4);
		feedTitle.setPadding(10, 5 , 0, 5);
		RelativeLayout.LayoutParams lpt = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lpt.addRule(RelativeLayout.BELOW, authorImage.getId());
		relLay.addView(feedTitle, lpt);
		
		View v = new View(context);
		v.setBackgroundColor(Color.WHITE);
		v.setId(5);
		RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,2);
		lpv.addRule(RelativeLayout.BELOW, authorImage.getId());
		lpv.addRule(RelativeLayout.BELOW, feedTitle.getId());
		relLay.addView(v,lpv);
		
		WebView wv = new WebView(context);
		wv.loadData(data, "text/html; charset=UTF-8", null);
		wv.setBackgroundColor(Color.BLACK);
		wv.setId(6);
		wv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String data){
				view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				view.setBackgroundColor(Color.BLACK);
				view.loadData(data, "text/html; charset=UTF-8", null);
				return true;
			}
		});
		RelativeLayout.LayoutParams lpweb = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		lpweb.addRule(RelativeLayout.BELOW,v.getId());
		relLay.addView(wv,lpweb);
		
		popup.setContentView(relLay);
		popup.update();
		popup.setFocusable(true);
		popup.showAtLocation(parent, Gravity.CENTER, new Point().x, new Point().y);
	}
}
