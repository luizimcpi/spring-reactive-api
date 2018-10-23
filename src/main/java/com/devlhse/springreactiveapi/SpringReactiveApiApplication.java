package com.devlhse.springreactiveapi;

import com.devlhse.springreactiveapi.component.CarRouteHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@SpringBootApplication
public class SpringReactiveApiApplication {

	@Bean
	RouterFunction<?> routes(CarRouteHandler routeHandles) {
		return RouterFunctions.route(
				RequestPredicates.GET("/cars"), routeHandles::allCars)
				.andRoute(RequestPredicates.GET("/cars/{carId}"), routeHandles::carById)
				.andRoute(RequestPredicates.GET("/cars/{carId}/events"), routeHandles::events);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApiApplication.class, args);
	}
}
