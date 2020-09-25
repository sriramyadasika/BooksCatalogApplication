package com.sample.bookcatalogservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.bookcatalogservice.exception.BookCatalogNotFoundException;
import com.sample.bookcatalogservice.model.BookCatalog;
import com.sample.bookcatalogservice.model.BooksInfo;
import com.sample.bookcatalogservice.model.BooksRating;
import com.sample.bookcatalogservice.service.BookCatalogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This is the controller class which serves the incoming request to get the
 * details of book catalog service.
 */

@RestController
@Api(value = "This API provides Book Catalog details")
public class BookCatalogResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookCatalogResource.class);

	@Autowired
	private BookCatalogService bookCatalogService;

	@ApiOperation(value = "Get List of Book Catalog details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			                @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/catalog/{userId}")
	public ResponseEntity<List<BookCatalog>> getCatalog(@PathVariable("userId") String userId) {

		LOGGER.info("Request to fetch the Book Catalog for userId: {}", userId);

		List<BookCatalog> bookCatalog = new ArrayList<>();
		
		
			List<BooksRating> booksRating = bookCatalogService.getBooksRatingForUser(userId);

			if(!booksRating.isEmpty()) {
				bookCatalog = booksRating.stream().map(rating -> {

					BooksInfo books = bookCatalogService.getBooksInfo(rating.getBookId());
					return new BookCatalog(books.getBookId(), books.getName(), rating.getRating());
				}).collect(Collectors.toList());
			}
			else {
				
			   throw new BookCatalogNotFoundException(userId);
		  
			}

		LOGGER.info("Response of Get Book Catalog for  userId: {}", bookCatalog);

		return new ResponseEntity<List<BookCatalog>>(bookCatalog, HttpStatus.OK);

	}

}
