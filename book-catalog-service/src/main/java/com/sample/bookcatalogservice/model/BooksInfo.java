package com.sample.bookcatalogservice.model;

/**
 * This class holds the properties of the 
 * bookInfo object.
 * */


public class BooksInfo {
	
	private String bookId;
	private String name;
	
	public BooksInfo() {
		
	}
	
	public BooksInfo(String bookId, String name) {
		this.bookId = bookId;
		this.name = name;
		
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
	
	

}
