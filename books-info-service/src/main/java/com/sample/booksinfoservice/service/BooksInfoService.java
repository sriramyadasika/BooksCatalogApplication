package com.sample.booksinfoservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.booksinfoservice.exception.BookNotFoundException;
import com.sample.booksinfoservice.model.Books;
import com.sample.booksinfoservice.repository.BooksInfoRepository;

@Service
public class BooksInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BooksInfoService.class);

	@Autowired
	BooksInfoRepository booksInfoRepository;

	/**
	 * This method fetches all the books from the database. *
	 * 
	 * @return list of books
	 */
	public List<Books> getBooks() {

		List<Books> books = new ArrayList<>();
		booksInfoRepository.findAll().forEach(book -> books.add(book));
		LOGGER.debug("Books fetched from Data Layer:{}", books);
		return books;
	}

	/**
	 * This method gets a specific book for a given bookId *
	 * 
	 * @return book details
	 * @exception Book Not Found if the bookId does not exist.
	 */
	public Books getBookById(String id) {
		return booksInfoRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	/**
	 * This method saves book object in the database and is called * from
	 * application main class so as to store the book details initially.
	 */

	public void save(Books books) {
		booksInfoRepository.save(books);
	}

}
