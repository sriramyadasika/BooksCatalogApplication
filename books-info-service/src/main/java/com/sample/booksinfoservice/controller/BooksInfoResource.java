package com.sample.booksinfoservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.booksinfoservice.model.Books;
import com.sample.booksinfoservice.service.BooksInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This controller class acts as a resource to provide the books details for an
 * incoming request.
 * 
 */

@RestController
@Api(value = "This API provides Books Information")
public class BooksInfoResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(BooksInfoResource.class);

	@Autowired
	BooksInfoService booksInfoService;

	@ApiOperation(value = "Get List of all books information ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			                @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/books")
	public List<Books> getBooksInfo() {
		List<Books> response = booksInfoService.getBooks();
		LOGGER.info("Response:{}", response);

		return booksInfoService.getBooks();

	}

	@ApiOperation(value = "Get book information for a given bookId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved book"),
			                @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/books/{bookId}")
	public Books getBookById(@PathVariable("bookId") String bookId) {
		LOGGER.info("Request for book with ID:{}", bookId);
		Books response = booksInfoService.getBookById(bookId);
		LOGGER.info("Response for book with ID:{}", response);

		return response;

	}

}
