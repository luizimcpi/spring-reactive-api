package com.devlhse.springreactiveapi.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class ApplicationRoutes {

    @Bean
    RouterFunction<?> routes(CarRouteHandler carRouteHandler, OwnerRouteHandler ownerRouteHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/owners/{ownerId}/cars"), carRouteHandler::allCars)
                .andRoute(RequestPredicates.GET("/owners/{ownerId}/cars/{carId}"), carRouteHandler::carById)
                .andRoute(RequestPredicates.POST("/owners/{ownerId}/cars"), carRouteHandler::createCar)
                .andRoute(RequestPredicates.DELETE("/owners/{ownerId}/cars/{carId}"), carRouteHandler::delete)
                .andRoute(RequestPredicates.GET("/owners"), ownerRouteHandler::allOwners)
                .andRoute(RequestPredicates.GET("/owners/{ownerId}"), ownerRouteHandler::ownerById)
                .andRoute(RequestPredicates.POST("/owners"), ownerRouteHandler::createOwner)
                .andRoute(RequestPredicates.DELETE("/owners/{ownerId}"), ownerRouteHandler::delete);
    }
}
