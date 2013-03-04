package com.ridwanadit.teladan48.postparser;

public class PostAuthor {
	
	private String name, gravatar, url;

	public void setName(String name) { this.name = name;}
	public void setURL (String url) {this.url = url; }
	public void setGravatar(String gravatar) { this.gravatar = gravatar; }

	public String getName () { return name; }
	public String getGravatar () { return gravatar; }
	public String getURL () {return url;}

}
