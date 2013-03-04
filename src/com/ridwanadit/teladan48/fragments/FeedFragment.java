package com.ridwanadit.teladan48.fragments;


import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ridwanadit.teladan48.feed.DynAdapterFeed;
import com.ridwanadit.teladan48.feed.DynLayoutFeed;
import com.ridwanadit.teladan48.feedparser.Feed;
import com.ridwanadit.teladan48.feedparser.FeedAuthor;
import com.ridwanadit.teladan48.feedparser.FeedPosts;
import com.ridwanadit.teladan48.feedparser.JSONFeedParser;
import com.ridwanadit.teladan48.feedparser.PopUp;

public class FeedFragment extends Fragment {

	String[] parts; 
	JSONFeedParser Fparser= new JSONFeedParser();
	ListView lv;
	DynAdapterFeed Adp = null;
	ArrayList<DynLayoutFeed> Lay = new ArrayList<DynLayoutFeed>();
	PopUp pop = new PopUp();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		lv = new ListView(getActivity());
		lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

		new FetchFeed().execute("http://teladan07.org/?wpapi=get_posts&count=20&page=1");
		
		return lv;

	}

	private class FetchFeed extends AsyncTask<String, Void, DynAdapterFeed>{
		ProgressDialog prog;

		@Override
		protected void onPreExecute() {
			prog = new ProgressDialog(getActivity());
			prog.setTitle("Fetching");
			prog.setMessage("Please Wait...");       
			prog.show();
		}

		@Override
		protected DynAdapterFeed doInBackground(String... url) {
			// TODO Auto-generated method stub
			Feed feed;
			String pUrl = url[0];
			Log.d("URL", pUrl);

			feed = Fparser.FeedParse(pUrl);
			for (FeedPosts feedpost : feed.getPosts()) {
				DynLayoutFeed listItem = new DynLayoutFeed();
				listItem.setFeedPostTitle(feedpost.getTitle());
				//Log.d("title", listItem.getFeedPostTitle());
				listItem.setDate(feedpost.getDate());
				//Log.d("Date", listItem.getDate());
				listItem.setContent(feedpost.getExcerpt());
				//Log.d("excerpt", listItem.getContent());
				listItem.setLink(feedpost.getUrl());
				//Log.d("URL", listItem.getLink());
				listItem.setFeedPostID(feedpost.getId());
				//Log.d("ID", listItem.getFeedPostID().toString());
				for (FeedAuthor feedauthor : feedpost.getAuthor()) {
					listItem.setAuthor(feedauthor.getName());
					listItem.setAuthorURL(feedauthor.getURL());
					listItem.setAuthorGravatar(feedauthor.getGravatar());
				}
				Lay.add(listItem);
			}
			Adp = new DynAdapterFeed(getActivity(), Lay);

			return Adp;

		}

		@Override
		protected void onPostExecute(DynAdapterFeed result) {
			prog.dismiss();			
			lv.setAdapter(Adp);
			for (DynLayoutFeed dyn : Lay){
				dyn.loadImage(Adp);
				Log.d("Gravatar", dyn.getAuthorGravatar());
			}
			
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					pop.ShowPop(getActivity(), lv, Adp,arg2);
				}
			});

		}
	}
	
	
}
