package com.sample.bookcatalogservice.exception;

public class BookCatalogNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public BookCatalogNotFoundException(String userId) {
        super("Book Catalog not found for UserId: " + userId);
    }
	

}
