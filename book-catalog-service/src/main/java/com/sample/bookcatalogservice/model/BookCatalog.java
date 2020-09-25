package com.sample.bookcatalogservice.model;

import io.swagger.annotations.ApiModelProperty;

/* This class acts as a POJO class with the 
 * details of the catalog object to be returned.
 * 
 * */
 

public class BookCatalog {
	
	@ApiModelProperty(notes = "Book Id", example = "1")
	private String bookId;
	
	@ApiModelProperty(notes = "Book Name", example = "Rest API Tutorial")
	private String name;
	
	@ApiModelProperty(notes = "Rating given for book by user", example = "5.0")
	private double rating;
	
	public BookCatalog(String bookId, String name, double rating) {
		this.bookId = bookId;
		this.name = name;
		this.rating = rating;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	

}
