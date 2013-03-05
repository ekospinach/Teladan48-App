package com.ridwanadit.teladan48.fragments;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import com.ridwanadit.teladan48.feed.DynAdapterFeed;
import com.ridwanadit.teladan48.feed.DynLayoutFeed;
import com.ridwanadit.teladan48.feedparser.Feed;
import com.ridwanadit.teladan48.feedparser.FeedAuthor;
import com.ridwanadit.teladan48.feedparser.FeedPosts;
import com.ridwanadit.teladan48.feedparser.JSONFeedParser;
import com.ridwanadit.teladan48.feedparser.PopUp;

public class FeedFragment extends Fragment {

	ListView lv;
	String[] parts;
	PopUp pop = new PopUp();
	DynAdapterFeed Adp = null;
	JSONFeedParser Fparser= new JSONFeedParser();
	ArrayList<DynLayoutFeed> Lay = new ArrayList<DynLayoutFeed>();

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
			Feed feed;
			String pUrl = url[0];
			Log.d("URL", pUrl);
			
			feed = Fparser.FeedParse(pUrl);
			for (FeedPosts feedpost : feed.getPosts()) {
				DynLayoutFeed listItem = new DynLayoutFeed();
				listItem.setFeedPostTitle(feedpost.getTitle());
				listItem.setDate(convertDate(feedpost.getDate()));
				listItem.setContent(feedpost.getExcerpt());
				listItem.setLink(feedpost.getUrl());
				listItem.setFeedPostID(feedpost.getId());
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
			}	
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					pop.ShowPop(getActivity(), lv, Adp,arg2);
				}
			});
		}
	}
	
	@SuppressLint("SimpleDateFormat")
	private String convertDate (String date) {
		final String OldFormat = "yyyy-MM-dd HH:mm:ss";
		final String NewFormat = "EEEE, dd-MM-yy, HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(OldFormat);
		try {
			Date d = sdf.parse(date);
			sdf.applyPattern(NewFormat);
			return sdf.format(d).toString();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}