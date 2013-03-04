package com.ridwanadit.teladan48.feedparser;

import java.util.List;

//Class to hold the Posts inside the JSON Feed
public class FeedPosts {

	private Integer id;
	private String url;
	private String title;
	private String date;
	private String excerpt;
	
	//object "author" inside the "posts" array of the JSON Feed is an array, thus it is defined as a List
	//Author.java defines the structure of the Author object;
	private List<FeedAuthor> author;

	public void setId (Integer id) { this.id = id; }
	public void setUrl (String url) { this.url = url; }
	public void setTitle (String title) { this.title = title; }
	public void setDate (String date) {this.date = date;}
	public void setExcerpt (String excerpt) {this.excerpt = excerpt; }
	public void setAuthor (List<FeedAuthor> author) {this.author = author;} 

	public Integer getId() { return id; }
	public String getUrl() { return url; }
	public String getTitle() { return title; }
	public String getDate() { return date; }
	public String getExcerpt() { return excerpt; }
	public List<FeedAuthor> getAuthor() { return author; }
	
	@Override
	public String toString() {
		String auth=author.toString();
		return "\r\n\r\n" + "ID : " + id + "\r\n" + "URL : " + url + "\r\n" + "title : " + title + "\r\n"
				+ "content : " + excerpt + "\r\n" + auth;
	}
}