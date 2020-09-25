package com.sample.booksinfoservice.repository;

import org.springframework.data.repository.CrudRepository;
import com.sample.booksinfoservice.model.Books;


public interface BooksInfoRepository extends CrudRepository<Books, String> {

}
