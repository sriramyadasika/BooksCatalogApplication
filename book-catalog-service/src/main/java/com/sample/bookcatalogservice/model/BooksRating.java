package com.sample.bookcatalogservice.model;

/** This POJO class holds the details of bookRating object
 * 
 * */
 

public class BooksRating {
	
	private String bookId;
	private double rating;
	
	public BooksRating() {
		
	}
	
	public BooksRating(String bookId, double rating) {
		this.bookId = bookId;
		this.rating = rating;
		
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	

}
