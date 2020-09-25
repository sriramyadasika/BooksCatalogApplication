package com.sample.booksratingservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.booksratingservice.model.BooksRating;
import com.sample.booksratingservice.service.BooksRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/** This controller class acts as a resource to provide the 
 *  book ratings for a given userId.
 * 
 * */

@RestController
@Api(value="This API provides the list of books user has rated")
public class BooksRatingResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BooksRatingResource.class);
	
	@Autowired
	BooksRatingService booksRatingService;
	
	
	@ApiOperation(value = "Get List of  books the user has rated ")
	@ApiResponses(value = {
	           @ApiResponse(code = 200, message = "Successfully retrieved list"),
	           @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	   })
	@GetMapping("/rating/{userId}")
    public List<BooksRating> getBooksRating(@PathVariable("userId") String userId) {
		
		LOGGER.info("Request to fetch book Ratings for userID: {}", userId);
		
		List<BooksRating> ratings = booksRatingService.getBooksRatingForUser(userId);
        
		LOGGER.info("Response for fetching the book ratings: {}",ratings);
		
        return ratings;

    }

}
