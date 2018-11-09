package com.devlhse.springreactiveapi.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class CarRoutes {

    @Bean
    RouterFunction<?> routes(CarRouteHandler carRouteHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/cars"), carRouteHandler::allCars)
                .andRoute(RequestPredicates.GET("/cars/{carId}"), carRouteHandler::carById)
                .andRoute(RequestPredicates.POST("/cars"), carRouteHandler::createCar)
                .andRoute(RequestPredicates.DELETE("/cars/{carId}"), carRouteHandler::delete);
    }
}
