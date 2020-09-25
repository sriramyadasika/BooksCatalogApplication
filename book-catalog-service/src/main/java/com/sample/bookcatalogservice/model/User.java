package com.sample.bookcatalogservice.model;

import java.util.List;

/** This POJO class holds the details of User object
 * to return the details of bookRatings for a given user
 * 
 * */

public class User {
	
	private String userId;
	private List<BooksRating> booksRating;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<BooksRating> getBooksRating() {
		return booksRating;
	}
	public void setBooksRating(List<BooksRating> booksRating) {
		this.booksRating = booksRating;
	}
	
	

}
