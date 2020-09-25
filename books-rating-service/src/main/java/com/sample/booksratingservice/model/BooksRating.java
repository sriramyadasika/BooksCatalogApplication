package com.sample.booksratingservice.model;

import io.swagger.annotations.ApiModelProperty;

public class BooksRating {
	
	@ApiModelProperty(notes = "Book Id", example = "1")
	private String bookId;
	
	@ApiModelProperty(notes = "Rating given by user", example = "3.5")
	private double rating;
	
	public BooksRating(String bookId, double rating ) {
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
