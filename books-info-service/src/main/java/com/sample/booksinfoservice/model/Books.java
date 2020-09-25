package com.sample.booksinfoservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

/** This class acts as an entity and is used to
 * store the Book Object details in the database.
 * */
 

@Entity  
 
@Table(name ="books")  
public class Books {

@Id  
@Column  
@ApiModelProperty(notes = "Book Id", example = "1")
private String bookId;  

@Column  
@ApiModelProperty(notes = "Book Name", example = "Spring Boot Tutorial")
private String name;

public Books() {}

public Books(String bookId, String name) {
	this.bookId = bookId;
	this.name = name;
	
}

public String getBookId() {
	return bookId;
}

public void setBookId(String bookId) {
	this.bookId = bookId;
}

public String getName() {
	return name;
}

public void setBookName(String name) {
	this.name = name;
}


}
