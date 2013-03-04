package com.ridwanadit.teladan48.feedparser;

//Defines the Author Class

public class FeedAuthor {
	private String name;
	private String gravatar;
	private String url;

	public void setName(String name) { this.name = name;}
	public void setURL (String url) {this.url = url; }
	public void setGravatar(String gravatar) { this.gravatar = gravatar; }

	public String getName () { return name; }
	public String getGravatar () { return gravatar; }
	public String getURL () {return url;}

	@Override
	public String toString() {
		return "Author Name " + name + "\r\n" + "Gravatar URL " + gravatar;
	}
}