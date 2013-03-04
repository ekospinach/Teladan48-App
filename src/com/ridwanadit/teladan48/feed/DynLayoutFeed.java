package com.ridwanadit.teladan48.feed;

import android.content.Context;

public class DynLayoutFeed {

	private Integer feedPostID;
	private String feedPostURL;
	private String feedPostDate;
	private String feedPostTitle;
	private String feedPostExcerpt;
	
	private String feedAuthorName;
	private String feedAuthorURL;
	private String feedAuthorGravatar;
	
	public DynLayoutFeed(Context context) {
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
	
}
