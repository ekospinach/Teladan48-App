package com.ridwanadit.teladan48.classes;

import java.io.InputStream;

import com.ridwanadit.teladan48.feed.DynAdapterFeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class FeedClass {
	private String title;
	private String content;
	private String creator;
	private String date;
	private String imgurl;
	private String link;
	private Bitmap image;
	private DynAdapterFeed adp;
	
	public FeedClass() {
		this.image = null;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setImgURL(String imgurl) {
		this.imgurl = imgurl;
	}
	
	public String getImgURL(){
		return imgurl;
	}
	
	public void setLink (String link) {
		this.link = link;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setAdapter(DynAdapterFeed adp) {
		this.adp=adp;
	}
	
	public DynAdapterFeed getAdapter() {
		return adp;
	}
	
	public Bitmap getImage() {
		return image;
	}
	
	public void loadImage(DynAdapterFeed adp) {
		this.adp=adp;
		if (imgurl!=null && !imgurl.equals("")){
			new ImageLoadTask().execute(imgurl);
		}
	}
	
	private class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				InputStream in = new java.net.URL(params[0]).openStream();
				Bitmap b = BitmapFactory.decodeStream(in);
				return b;
			} catch (Exception e) {
				return null;
			}			
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			if (result!=null) {
				image = result;
				if (adp!=null){
					adp.notifyDataSetChanged();
				}
			}
		}
		
	}
	
}
