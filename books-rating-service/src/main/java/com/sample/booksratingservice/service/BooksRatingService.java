package com.sample.booksratingservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sample.booksratingservice.exception.BooksRatingNotFoundException;
import com.sample.booksratingservice.model.BooksRating;

/** This class is a service class to store the user and book ratings details in a collection
 *  and return the details for a given userId.
 *  
 * */

@Service
public class BooksRatingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BooksRatingService.class);
	

	public List<BooksRating> getBooksRatingForUser(String userId) {
		
		Map<String, List<BooksRating>> booksRatingData = getBooksRatingData();
		
		LOGGER.debug("Book Ratings Data : {} , for userId: {}", booksRatingData, userId);
		
		// Stream through the entrySet of booksRating Map and returns the filtered values corresponding to the userId provided
		// if userId present else will throw exception
		
		Optional<List<BooksRating>> booksRating = booksRatingData.entrySet().stream()
				                       .filter(e -> e.getKey().equalsIgnoreCase(userId))
				                       .map(Map.Entry::getValue)
				                       .findFirst();
			 
		
        return booksRating.orElseThrow(() -> new BooksRatingNotFoundException(userId));
	 
	}

	private Map<String, List<BooksRating>> getBooksRatingData() {

		Map<String, List<BooksRating>> booksRatingData = new HashMap<>();
		
		// storing the data in Map collection with key as userId and value as the list of booksRating object
		
		booksRatingData.put("user1", Arrays.asList(new BooksRating("1", 3), new BooksRating("2", 5)));
		booksRatingData.put("user2", Arrays.asList(new BooksRating("1", 3.5), new BooksRating("2", 4), 
				                                   new BooksRating("3", 5)));
		booksRatingData.put("user3", Arrays.asList(new BooksRating("1", 4), new BooksRating("2", 4), 
                                                   new BooksRating("3", 5), new BooksRating("4", 1)));
		booksRatingData.put("user4", Arrays.asList(new BooksRating("1", 3.5), new BooksRating("2", 4), 
                                                   new BooksRating("3", 5),new BooksRating("4", 5), new BooksRating("6", 2)));
		
		return booksRatingData;
	}
	
}
