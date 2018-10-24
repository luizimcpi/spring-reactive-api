package com.devlhse.springreactiveapi.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class Routers {

    @Bean
    RouterFunction<?> routes(CarRouteHandler routeHandles) {
        return RouterFunctions.route(
                RequestPredicates.GET("/cars"), routeHandles::allCars)
                .andRoute(RequestPredicates.GET("/cars/{carId}"), routeHandles::carById)
                .andRoute(RequestPredicates.GET("/cars/{carId}/events"), routeHandles::events);
    }
}
