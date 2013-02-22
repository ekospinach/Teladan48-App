package com.ridwanadit.teladan48.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.ridwanadit.teladan48.xml.Channel;
import com.ridwanadit.teladan48.xml.Item;
import com.ridwanadit.teladan48.xml.Items;
import com.ridwanadit.teladan48.xml.ParseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.datatype.Duration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ridwanadit.teladan48.adapter.DynAdapterFeed;
import com.ridwanadit.teladan48.adapter.DynLayoutFeed;

public class FeedFragment extends Fragment {

	ParseHandler ph = new ParseHandler();
	Channel channel = new Channel();
	Item item = new Item();
	Items items = new Items();
	String[] parts; 

	Bitmap bmp;

	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> creator = new ArrayList<String>();
	ArrayList<String> content = new ArrayList<String>();
	ArrayList<String> date = new ArrayList<String>();
	ArrayList<String> imgurl = new ArrayList<String>();
	ArrayList<String> link = new ArrayList<String>();

	ScrollView scrl;
	LinearLayout ll;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ll = new LinearLayout (getActivity());
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		ll.setOrientation(LinearLayout.VERTICAL);

		scrl = new ScrollView (getActivity());
		scrl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		scrl.setFillViewport(true);

		new FetchFeed().execute("http://teladan07.org/feed");
		ll.addView(scrl);
		return ll;

	}

	public ScrollView loadItems (String title, String creator, String date, String content, String url) {
		return scrl;
	}

	private class FetchFeed extends AsyncTask<String, Void, ScrollView>{
		ProgressDialog prog;
		DynLayoutFeed listItem = new DynLayoutFeed(getActivity());
		DynAdapterFeed li = new DynAdapterFeed(getActivity());


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
			// TODO Auto-generated method stub
			String pUrl = url[0];
			Log.d("a", "masuk");
			//Validate.isTrue(url.length()>1,"need to specify URL");
			Document doc;
			String temp;

			try {
				InputStream in = new java.net.URL(pUrl).openStream();
				items = ph.parse(in);
				for (int i=0; i<items.size(); i++) {
					//Log.d("Item "+i, items.get(i).getTitle().toString());
					title.add(items.get(i).getTitle().toString());
					creator.add(items.get(i).getCreator().toString());
					if (items.get(i).getDescription().toString().contains(">")){
						parts=items.get(i).getDescription().toString().split(">");
						content.add(parts[1]);
						temp = parts[0]+">";
						doc = Jsoup.parse(temp);
						Elements media = doc.select("[src]");
						imgurl.add(media.attr("abs:src").toString());
					}
					else {
						imgurl.add("no image");
						content.add(items.get(i).getDescription().toString());
					}
					//Log.d("Image", imgurl.get(i));
					date.add(items.get(i).getPubDate().toString());
					link.add(items.get(i).getLink().toString());
				}


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.d("a", ""+strlist.size());
			for (int i = 0; i<items.size(); i++){
				if (imgurl.get(i).toString().equals("no image")){
					listItem.setInfo(getActivity(), i,
							title.get(i).toString(),
							creator.get(i).toString(), 
							date.get(i).toString(),
							content.get(i).toString(),null,
							link.get(i).toString());					
				}
				else {
					try {
						InputStream in = new java.net.URL(imgurl.get(i).toString()).openStream();
						bmp = BitmapFactory.decodeStream(in);
						listItem.setInfo(getActivity(), i,
								title.get(i).toString(),
								creator.get(i).toString(), 
								date.get(i).toString(),
								content.get(i).toString(),bmp,
								link.get(i).toString());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Log.d("Link", listItem.getId()+"");

			}

			return scrl;

		}

		@Override
		protected void onPostExecute(ScrollView result) {
			// TODO Auto-generated method stu

			prog.dismiss();
			scrl.addView(listItem);
			super.onPostExecute(result);
		}
	}
}
