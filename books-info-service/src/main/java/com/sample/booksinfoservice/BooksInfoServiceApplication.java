package com.sample.booksinfoservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.sample.booksinfoservice.model.Books;
import com.sample.booksinfoservice.service.BooksInfoService;

@SpringBootApplication
@EnableEurekaClient
public class BooksInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksInfoServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(BooksInfoService service) {
		return args -> {
			service.save(new Books("1", "Wings of Fire"));
			service.save(new Books("2", "David Copperfield"));
			service.save(new Books("3", "Moby Dick "));
			service.save(new Books("4", "Lord of the Rings"));
			service.save(new Books("5", "Gulliver’s Travels "));
			service.save(new Books("6", "Adventures of Huckleberry Finn"));
		};
	}
}
