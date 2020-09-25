package com.sample.bookcatalogservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.bookcatalogservice.controller.BookCatalogResource;
import com.sample.bookcatalogservice.exception.BookCatalogNotFoundException;
import com.sample.bookcatalogservice.model.BookCatalog;
import com.sample.bookcatalogservice.service.BookCatalogService;


@RunWith(SpringRunner.class)
@WebMvcTest(BookCatalogResource.class)
class BookCatalogResourceTest {
	
	 @Autowired
	  private MockMvc mvc;
	   
	  @MockBean
	  private BookCatalogResource bookCatalogResource;
	   
	  @MockBean
	  private BookCatalogService service;
	   
	   @Test
	   void shouldGetBookCatalogForGivenUserId() throws Exception {
		   // given
	       
		   String userId = "user3";
	       List<BookCatalog> books = buildResponseData(); 
	       given(bookCatalogResource.getCatalog(userId)).willReturn(new ResponseEntity<List<BookCatalog>>(books, HttpStatus.OK));
	       	       
	       // when
	       
	       mvc.perform(get("/catalog/{userId}", userId))
	               .andExpect(jsonPath("$", hasSize(2)))
	               .andExpect(jsonPath("$[0].bookId", is("1")))
	               .andExpect(jsonPath("$[0].name", is("java programming")))
	               .andExpect(jsonPath("$[0].rating", is(3.0)))
	               .andExpect(jsonPath("$[1].bookId", is("2")))
	               .andExpect(jsonPath("$[1].name", is("Restful Webservices")))
	               .andExpect(jsonPath("$[1].rating", is(4.5)));
	                     
	       // then
	       
	      verify(bookCatalogResource, times(1)).getCatalog(userId);
	      
	   }
	      
	 @Test
     void shouldReturnBookCatalogNotFoundForId() throws Exception {
	   // given
	   
	   String userId = "user5"; 
       given(bookCatalogResource.getCatalog(userId)).willThrow(new BookCatalogNotFoundException(userId));
       
       // when
     
       MockHttpServletResponse response =  mvc.perform(get("/catalog/{userId}", userId))
    		   .andReturn().getResponse();
       
       // then
       
       assertThat(response.getStatus(), is(HttpStatus.BAD_REQUEST.value()));                   
       verify(bookCatalogResource, times(1)).getCatalog(userId);
       
	 }
                  
	            
	private List<BookCatalog> buildResponseData() {
		
		List<BookCatalog> catalog = new ArrayList<>();
		catalog.add(new BookCatalog("1", "java programming", 3));
		catalog.add(new BookCatalog("2", "Restful Webservices", 4.5));
		return catalog;
	}

	

}
