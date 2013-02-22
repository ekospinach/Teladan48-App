package com.ridwanadit.teladan48.fragments;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ridwanadit.teladan48.adapter.DynAdapter;
import com.ridwanadit.teladan48.adapter.DynLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class TweetFragment extends Fragment {
	
	ArrayList<String> strlist = new ArrayList<String>();
	ArrayList<String> imglist = new ArrayList<String>();
	
	Bitmap bmp = null;
	
	ScrollView scrl;
	EditText text;
	LinearLayout ll;
	ProgressDialog prog;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		ll = new LinearLayout (getActivity());
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		ll.setOrientation(LinearLayout.VERTICAL);
		
		text = new EditText (getActivity());
		text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				
		final Button btn = new Button(getActivity());
		btn.setText("Search");
		btn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		btn.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				getActivity();
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(text.getWindowToken(),0);
				strlist.clear();				
				scrl.removeAllViews();
				ll.removeView(scrl);
				String url = text.getText().toString();
				new FetchTask().execute("http://ragial.com/search/iRO-Renewal/"+url);
				ll.addView(scrl);
			}
		});
		
		
		ll.addView(text);
		ll.addView(btn);
		
		scrl = new ScrollView (getActivity());
		scrl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		scrl.setFillViewport(true);
		
		//new FetchTask().execute("http://ragial.com/search/iRO-Renewal/zweihander");
		Log.d("a", ""+strlist.size());
		
		return ll;
		
		
	}
	
	
	private class FetchTask extends AsyncTask<String, Void, ScrollView>{


		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			 
			    prog = new ProgressDialog(getActivity());
			    prog.setTitle("Fetching");
			    prog.setMessage("Please Wait...");       
			    prog.show();
		}

		@Override
		protected ScrollView doInBackground(String... url) {
			String pUrl = url[0];
			Log.d("a", "masuk");
			//Validate.isTrue(url.length()>1,"need to specify URL");
			
			Document doc;
			try {
				doc = Jsoup.connect(pUrl).userAgent("mozilla").get();
				Elements links  = doc.select("a[href*=/item/]");
				Elements media = doc.select("img[src*=/item/]");
				for (Element link : links) {
					strlist.add(link.text());
					Log.d("items", link.text());
		        }
				Log.d("a", "I'm in");
				for (Element img : media ) {
					imglist.add(img.attr("abs:src"));
					Log.d("images", img.attr("abs:src"));
				}
				InputStream in = new java.net.URL(imglist.get(1).toString()).openStream();
				bmp = BitmapFactory.decodeStream(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.d("a", ""+strlist.size());
			return scrl;
			
		}
	
		@Override
		protected void onPostExecute(ScrollView result) {
			// TODO Auto-generated method stub
			prog.dismiss();
			
			DynLayout listItem = new DynLayout(getActivity());
			DynAdapter li = new DynAdapter(getActivity());
			
			
			scrl.addView(listItem);
			li.clearlist();

			int i;
	
			for (i=0;i<strlist.size();i=i+2){
				listItem.setInfo(getActivity(), strlist.get(i)+"\r\n\r\n"+strlist.get(i+1), bmp);
				//Log.d("a", strlist.get(i));
				li.additem(listItem);
			}
			
			
			listItem.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(),"bisa" , Toast.LENGTH_SHORT).show();
					
				}
			});
			super.onPostExecute(result);
			
		}
	}
}
