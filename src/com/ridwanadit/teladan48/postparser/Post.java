package com.ridwanadit.teladan48.postparser;

import java.util.List;

public class Post {
	private String status;
	private List<PostPosts> posts;
	
	public void setStatus (String status) { this.status = status; }
	public void setPosts (List<PostPosts> posts) {this.posts = posts; }
	
	public String getStatus() {return status;}
	public List<PostPosts> getPosts() {return posts;}
}
