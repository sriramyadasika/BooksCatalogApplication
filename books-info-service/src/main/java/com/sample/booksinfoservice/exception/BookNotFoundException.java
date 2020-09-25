package com.sample.booksinfoservice.exception;

public class BookNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	

	public BookNotFoundException(String bookId) {
        super("Book not found for Id: " + bookId);
    }
	

}
