package com.sample.bookcatalogservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sample.bookcatalogservice.model.BooksInfo;
import com.sample.bookcatalogservice.model.BooksRating;

@Service
public class BookCatalogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookCatalogService.class);

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "callBookRatingsFallBackMethod")
	public List<BooksRating> getBooksRatingForUser(String userId) {

		List<BooksRating> booksRating = new ArrayList<>();

		try {

			ResponseEntity<List<BooksRating>> response = restTemplate.exchange(
					"http://books-rating-service/rating/" + userId, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<BooksRating>>() {
					});

			booksRating = response.getBody();

		} catch (RestClientException e) {

			LOGGER.error("Error fetching books rating details due to: {}", e.getMessage());

		}

		return booksRating;
	}

	@HystrixCommand(fallbackMethod = "callBooksInfoFallBackMethod")
	public BooksInfo getBooksInfo(String bookId) {

		BooksInfo booksInfoResponse = null;

		try {

			booksInfoResponse = restTemplate.getForObject("http://books-info-service/books/" + bookId, BooksInfo.class);

		} catch (RestClientException e) {

			LOGGER.error("Error fetching books rating details due to: {}", e.getMessage());

		}

		return booksInfoResponse;
	}

	@SuppressWarnings("unused")
	private BooksInfo callBooksInfoFallBackMethod(String bookId) {

		BooksInfo booksInfo = new BooksInfo("", "");

		LOGGER.info("Books Info Service is down!!! fallback route enabled...");

		return booksInfo;
	}

	@SuppressWarnings("unused")
	private List<BooksRating> callBookRatingsFallBackMethod(String userId) {

		List<BooksRating> rating = new ArrayList<>();
		rating.add(new BooksRating("", 0));

		LOGGER.info("Books Rating Service is down!!! fallback route enabled...");

		return rating;
	}

}
