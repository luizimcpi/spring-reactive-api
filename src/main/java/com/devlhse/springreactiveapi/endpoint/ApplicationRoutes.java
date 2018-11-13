package com.devlhse.springreactiveapi.endpoint;

/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ApplicationRoutes {

    @Bean
    RouterFunction<?> ownerRoutes(OwnerRouteHandler ownerRouteHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/owners"), ownerRouteHandler::allOwners)
                .andRoute(RequestPredicates.GET("/owners/{ownerId}"), ownerRouteHandler::ownerById)
                .andRoute(RequestPredicates.POST("/owners").and(accept(MediaType.APPLICATION_JSON)), ownerRouteHandler::createOwner)
                .andRoute(RequestPredicates.DELETE("/owners/{ownerId}"), ownerRouteHandler::delete);
    }

    @Bean
    RouterFunction<?> carRoutes(CarRouteHandler carRouteHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/owners/{ownerId}/cars"), carRouteHandler::allCars)
                .andRoute(RequestPredicates.GET("/owners/{ownerId}/cars/{carId}"), carRouteHandler::carById)
                .andRoute(RequestPredicates.POST("/owners/{ownerId}/cars").and(accept(MediaType.APPLICATION_JSON)), carRouteHandler::createCar)
                .andRoute(RequestPredicates.DELETE("/owners/{ownerId}/cars/{carId}"), carRouteHandler::delete);
    }*
}*/
