package com.sample.booksinfoservice;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

import com.sample.booksinfoservice.controller.BooksInfoResource;
import com.sample.booksinfoservice.exception.BookNotFoundException;
import com.sample.booksinfoservice.model.Books;
import com.sample.booksinfoservice.service.BooksInfoService;


@RunWith(SpringRunner.class)
@WebMvcTest(BooksInfoResource.class)
class BooksInfoResourceTest {
		
   @Autowired
   private MockMvc mvc;
   
   @MockBean
   private BooksInfoResource booksInfoResource;
   
   @MockBean
   private BooksInfoService service;
   
   @Test
   void shouldGetAllBooks() throws Exception {
	   // given
       
       List<Books> books = buildResponseData();     
       given(booksInfoResource.getBooksInfo()).willReturn(books);
       
       // when
       
       mvc.perform(get("/books"))
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].bookId", is("1")))
               .andExpect(jsonPath("$[0].name", is("java programming")))
               .andExpect(jsonPath("$[1].bookId", is("2")))
               .andExpect(jsonPath("$[1].name", is("Restful Webservices")))
               .andExpect(jsonPath("$[2].bookId", is("3")))
               .andExpect(jsonPath("$[2].name", is("Springboot tutorial")));
       
       // then
       
      verify(booksInfoResource, times(1)).getBooksInfo();
       
            
   }
   
   @Test
   void shouldGetOneBookById() throws Exception {
	   // given
	   
	   String bookId ="3";      
       Books book = new Books(bookId, "java programming");           
       given(booksInfoResource.getBookById(bookId)).willReturn(book);
       
       // when
     
       mvc.perform(get("/books/{bookId}", bookId))
               .andExpect(jsonPath("$.bookId", is(book.getBookId())))
               .andExpect(jsonPath("$.name", is(book.getName())));  
       
       // then
       
      verify(booksInfoResource, times(1)).getBookById(bookId);
       
            
   }
   
   @Test
   void shouldReturnNotFoundById() throws Exception {
	   // given
	   
	   String bookId ="3";   
       given(booksInfoResource.getBookById(bookId)).willThrow(new BookNotFoundException(bookId));
       
       // when
     
       MockHttpServletResponse response =  mvc.perform(get("/books/{bookId}", bookId))
    		   .andReturn().getResponse();
       
       // then
       
       assertThat(response.getStatus(), is(HttpStatus.BAD_REQUEST.value()));                   
       verify(booksInfoResource, times(1)).getBookById(bookId);
       
            
   }
   

private List<Books> buildResponseData() {
	   List<Books> books = new ArrayList<>();     
       books.add(new Books("1","java programming"));
       books.add(new Books("2","Restful Webservices"));
       books.add(new Books("3","Springboot tutorial"));
	   return books;
          
	   
}


}