package com.sample.booksratingservice.model;

import java.util.List;

public class User {
	
	private String userId;
	private List<BooksRating> ratings;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<BooksRating> getRatings() {
		return ratings;
	}
	public void setRatings(List<BooksRating> ratings) {
		this.ratings = ratings;
	}
	

}
