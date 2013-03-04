package com.ridwanadit.teladan48.fragments;

import java.io.InputStream;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class TweetFragment extends Fragment{
	String url;
	LinearLayout ll;	
	
	
	@SuppressWarnings("unused")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ll = new LinearLayout (getActivity());
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		ll.setOrientation(LinearLayout.VERTICAL);
		
		TextView tv = new TextView (getActivity());
		new RetrieveJSON().execute(url);

		return ll;
	}

	public class RetrieveJSON extends AsyncTask<String, Void, InputStream> {

		@Override
		protected InputStream doInBackground(String... params) {
			try{
				InputStream in = new java.net.URL(params[0]).openStream();
				JsonFactory factory = new JsonFactory();
				JsonParser parser = factory.createJsonParser(in);
				
				if (parser.nextToken() != JsonToken.START_OBJECT){
					
				} else {
					while (parser.nextToken()!=JsonToken.END_OBJECT) {
						@SuppressWarnings("unused")
						String fieldname = parser.getCurrentName();
						parser.nextToken();
					}
				}
				
			} catch (Exception e) {

			}
			// TODO Auto-generated method stub
			return null;
		}

	}
}
