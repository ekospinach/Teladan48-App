package com.ridwanadit.teladan48.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

public class CrawledList extends AsyncTask<String, Void, ArrayList<String>>{
	
	ArrayList<String> pass;
	
	public CrawledList(){

	}

	@Override
	protected ArrayList<String> doInBackground(String... url) {
		// TODO Auto-generated method stub
		String pUrl = url[0];
		pass = new ArrayList<String>();
		//Validate.isTrue(url.length()>1,"need to specify URL");

		Document doc;
		try {
			doc = Jsoup.connect(pUrl).userAgent("mozilla").get();
			Elements links  = doc.select("a[href]");
			for (Element link : links) {
				pass.add(link.attr("abs:href")+","+link.text());
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pass;
	}
	
	protected void onPostExecute(ArrayList<String> result) {
     onPostExecute(result);
        result=this.pass;
    }
}
