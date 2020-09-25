package com.sample.booksratingservice.exception;


public class BooksRatingNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	

	public BooksRatingNotFoundException(String userId) {
        super("Book Ratings not found for User: " + userId);
        
    }
}
