package com.sample.booksratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BooksRatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksRatingServiceApplication.class, args);
	}

}
