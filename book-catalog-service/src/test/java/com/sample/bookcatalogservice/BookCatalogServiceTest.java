package com.sample.bookcatalogservice;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.test.context.junit4.SpringRunner;

import com.sample.bookcatalogservice.model.BooksInfo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookCatalogServiceTest {
	
	 
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
	int randomServerPort;
	 
  
   
    @Test                                                                                          
    void ShouldReturnBooksInfoForGivenBookId() throws URISyntaxException {   
    
    	String bookId = "4";
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/" + bookId;
    	
            URI uri = new URI(baseUrl);
                 
            BooksInfo response = restTemplate.getForObject(uri, BooksInfo.class);
         
            assertNotNull(response);
    }
    
 
}
