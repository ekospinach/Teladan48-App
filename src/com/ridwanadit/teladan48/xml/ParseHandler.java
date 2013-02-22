package com.ridwanadit.teladan48.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;

import com.ridwanadit.teladan48.xml.Item;
import com.ridwanadit.teladan48.xml.Items;
import com.ridwanadit.teladan48.xml.Channel;

public class ParseHandler extends DefaultHandler {
	
	private Channel channel;
	private Items items;
	private Item item;
	
	public ParseHandler() {
		items = new Items();
	}
	
	public Items parse (InputStream is) {
		RootElement root = new RootElement("rss");
		Element chanElement = root.getChild("channel");
		
		Element chanItem = chanElement.getChild("item");
        Element itemTitle = chanItem.getChild("title");
        Element itemDescription = chanItem.getChild("description");
        Element itemLink = chanItem.getChild("link");
        Element itemCreator = chanItem.getChild("http://purl.org/dc/elements/1.1/","creator");
        Element itemDate = chanItem.getChild("pubDate");
        
        chanElement.setStartElementListener(new StartElementListener() {
			
			@Override
			public void start(Attributes attributes) {
				// TODO Auto-generated method stub
				channel = new Channel();
			}
		});
        
        
        chanItem.setStartElementListener(new StartElementListener() {
			
			@Override
			public void start(Attributes attributes) {
				// TODO Auto-generated method stub
				item = new Item();
			}
		});
        
        chanItem.setEndElementListener(new EndElementListener() {
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				items.add(item);	
			}
		});
        
        itemTitle.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				// TODO Auto-generated method stub
				item.setTitle(body);
			}
		});
        
        itemLink.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				// TODO Auto-generated method stub
				item.setLink(body);
			}
		});
        
        itemCreator.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				// TODO Auto-generated method stub
				item.setCreator(body);
			}
		});
        
        itemDescription.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				// TODO Auto-generated method stub
				item.setDescription(body);
			}
		});
        
        itemDate.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				// TODO Auto-generated method stub
				item.setPubDate(body);
			}
		});
       
        try {
            Xml.parse(is, Xml.Encoding.UTF_8, root.getContentHandler());
//            for (int i=0; i<items.size(); i++) {
//            	//Log.d("Item "+i, items.get(i).getTitle().toString());
//            	Log.d("Item "+i, items.get(i).getCreator().toString());
//            }
            return items;
        } catch (SAXException e) {
            // handle the exception
        } catch (IOException e) {
            // handle the exception
        }
		return null;
	}
}
