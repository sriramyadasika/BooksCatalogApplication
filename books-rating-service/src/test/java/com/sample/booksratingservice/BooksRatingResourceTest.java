package com.sample.booksratingservice;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.hamcrest.MatcherAssert.assertThat;

import com.sample.booksratingservice.controller.BooksRatingResource;
import com.sample.booksratingservice.exception.BooksRatingNotFoundException;
import com.sample.booksratingservice.model.BooksRating;
import com.sample.booksratingservice.service.BooksRatingService;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksRatingResource.class)
class BooksRatingResourceTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BooksRatingResource booksRatingResource;
	   
	@MockBean
	 private BooksRatingService service;
	
	 @Test
	  void shouldGetBooksRatingByUserId() throws Exception {
		 // given
		 
		   String userId ="user3";	       
	       List<BooksRating> booksRating = Arrays.asList(new BooksRating("1", 3.5), new BooksRating("2", 4), 
                                                         new BooksRating("3", 5));   
	       given(booksRatingResource.getBooksRating(userId)).willReturn(booksRating);
	       
	       // when      
	     
	       mvc.perform(get("/rating/{userId}", userId))
	               .andExpect(jsonPath("$", hasSize(3)))
	               .andExpect(jsonPath("$.[0]bookId", is("1")))
	               .andExpect(jsonPath("$.[0]rating", is(3.5)))
	               .andExpect(jsonPath("$.[1]bookId", is("2")))
	               .andExpect(jsonPath("$.[1]rating", is(4.0)))
	               .andExpect(jsonPath("$.[2]bookId", is("3")))
	               .andExpect(jsonPath("$.[2]rating", is(5.0)));
	       
	       // then
	       
	      verify(booksRatingResource, times(1)).getBooksRating(userId);
	       
	            
	   }
	   
	   @Test
	   void shouldReturnBooksRatingNotFound() throws Exception {	
		   // given
		   
		   String userId ="user6";	          
	       given(booksRatingResource.getBooksRating(userId)).willThrow(new BooksRatingNotFoundException(userId));
	       
	       // when
	     
	       MockHttpServletResponse response =  mvc.perform(get("/rating/{userId}", userId))
	    		   .andReturn().getResponse();
	       
	       // then
	       
	       assertThat(response.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
	       verify(booksRatingResource, times(1)).getBooksRating(userId);
	       
	            
	   }

	
}
