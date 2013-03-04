package com.ridwanadit.teladan48.feedparser;

import java.util.List;

//Define JSONFeed Structure
public class Feed {

	private String status;
	private String count;
	private String count_total;
	private String pages;
	private String currPage;
	
	//Result object "posts" in JSON Feed contains Array, thus it is defined as a list
	//Posts.java defines the structure of the Posts object
	private List<FeedPosts> posts;

	public void setStatus(String status) { this.status = status; }
	public void setCount(String count) { this.count = count; }
	public void setCount_Total(String count_total) {this.count_total = count_total;}
	public void setPages (String pages) { this.pages = pages; }
	public void setCurrPage (String currPage) { this.currPage = currPage; }
	public void setPosts (List<FeedPosts> posts) {this.posts = posts; }

	public String getStatus() {	return status;	}
	public String getCount() { return count;	}
	public String getCount_Total () { return count_total; }
	public String getPages () { return pages; }
	public String getCurrPage () { return currPage; }
	public List<FeedPosts> getPosts () { return posts; }
	
	@Override
	public String toString () {
		String post = posts.toString();
		return status + " " + count + " " + count_total + " " + pages + " " + currPage + "\r\n" + post;
	}

}
