package com.devlhse.springreactiveapi;

import com.devlhse.springreactiveapi.endpoint.CarRouteHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@SpringBootApplication
public class SpringReactiveApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApiApplication.class, args);
	}
}
