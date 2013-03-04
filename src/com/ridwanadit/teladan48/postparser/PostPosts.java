package com.ridwanadit.teladan48.postparser;

import java.util.List;


public class PostPosts {

		private Integer id;
		private String url, title, date, excerpt;
		
		//object "author" inside the "posts" array of the JSON Feed is an array, thus it is defined as a List
		//Author.java defines the structure of the Author object;
		private List<PostAuthor> author;

		public void setId (Integer id) { this.id = id; }
		public void setUrl (String url) { this.url = url; }
		public void setTitle (String title) { this.title = title; }
		public void setDate (String date) {this.date = date;}
		public void setExcerpt (String excerpt) {this.excerpt = excerpt; }
		public void setAuthor (List<PostAuthor> author) {this.author = author;} 

		public Integer getId() { return id; }
		public String getUrl() { return url; }
		public String getTitle() { return title; }
		public String getDate() { return date; }
		public String getExcerpt() { return excerpt; }
		public List<PostAuthor> getAuthor() { return author; }

}
