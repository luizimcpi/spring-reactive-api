package com.devlhse.springreactiveapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringReactiveApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApiApplication.class, args);
	}
}
