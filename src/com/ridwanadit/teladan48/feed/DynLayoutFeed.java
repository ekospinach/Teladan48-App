package com.ridwanadit.teladan48.feed;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class DynLayoutFeed {

	private Integer feedPostID;
	private String feedPostURL;
	private String feedPostDate;
	private String feedPostTitle;
	private String feedPostExcerpt;
	
	private String feedAuthorName;
	private String feedAuthorURL;
	private String feedAuthorGravatar;
	
	private Bitmap authorImage;
	private DynAdapterFeed adp;
	
	public DynLayoutFeed() {
	}
	
	public void setFeedPostTitle(String feedPostTitle) {
		this.feedPostTitle = feedPostTitle;
	}
	
	public String getFeedPostTitle() {
		return feedPostTitle;
	}
	
	public void setContent(String content) {
		this.feedPostExcerpt = content;
	}
	
	public String getContent() {
		return feedPostExcerpt;
	}
	
	public void setAuthor(String author) {
		this.feedAuthorName = author;
	}
	
	public String getAuthor() {
		return feedAuthorName;
	}
	
	public void setDate(String date) {
		this.feedPostDate=date;
	}
	
	public String getDate() {
		return feedPostDate;
	}
	
	public void setLink (String link) {
		this.feedPostURL = link;
	}
	
	public String getLink() {
		return feedPostURL;
	}
	
	public Integer getFeedPostID() {
		return feedPostID;
	}

	public void setFeedPostID(Integer id) {
		this.feedPostID = id;
	}

	public String getAuthorURL() {
		return feedAuthorURL;
	}

	public void setAuthorURL(String feedAuthorURL) {
		this.feedAuthorURL = feedAuthorURL;
	}

	public String getAuthorGravatar() {
		return feedAuthorGravatar;
	}

	public void setAuthorGravatar(String feedAuthorGravatar) {
		this.feedAuthorGravatar = feedAuthorGravatar;
	}
	
	public void setAdapter (DynAdapterFeed adp) {
		this.adp = adp;
	}
	
	public DynAdapterFeed getAdapter() {
		return adp;
	}
	
	public Bitmap getImage() {
		return authorImage;
	}
	
	public void loadImage(DynAdapterFeed adp) {
		this.adp = adp;
		Log.d("Masuk", feedAuthorGravatar);
		if (feedAuthorGravatar != null && !feedAuthorGravatar.equals("")) {
			new imageLoadTask().execute(feedAuthorGravatar);
		}
	}
	
	private class imageLoadTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
				try {
					Log.d("Image", "Begin processing image");
					InputStream in = new java.net.URL(params[0]).openStream();
					Bitmap bmp = BitmapFactory.decodeStream(in);
					return bmp;
				} catch (IOException e) {
					return null;
				}
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result!=null) {
				authorImage = result;
				if (adp!=null) {
					adp.notifyDataSetChanged();
				}
			} else {
			}
		}
		
	}
}
