package com.ridwanadit.teladan48.xml;

import java.io.Serializable;

public class Item implements Serializable {

	private Items items;
    private String title;
    private String link;
    private String description;
    private String pubDate;
    private String docs;
    private String creator;
    
    public Item() {
    	setItems (null);
    	setTitle (null);
    	setLink (null);
    	setDescription (null);
    	setPubDate (null);
    	setDocs(null);
    	setCreator(null);
    }
    
    public void setItems (Items items) {
    	this.items = items;
    }
    
    public Items getItems () {
    	return items;
    }
    
    public void setTitle (String title) {
    	this.title = title;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public void setLink (String link) {
    	this.link = link;
    }
    
    public String getLink() {
    	return link;
    }
    
    public void setDescription (String description) {
    	this.description = description;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDocs (String docs) {
    	this.docs = docs;
    }
    
    public String getDocs () {
    	return docs;
    }
    
    public void setPubDate (String pubDate) {
    	this.pubDate = pubDate;
    }
    
    public String getPubDate () {
    	return pubDate;
    }
    
    public void setCreator (String creator) {
    	this.creator = creator;
    }
    
    public String getCreator () {
    	return creator;
    }
    
    
}

