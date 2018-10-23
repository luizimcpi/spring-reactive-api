package com.devlhse.springreactiveapi.component;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.model.CarEvents;
import com.devlhse.springreactiveapi.service.FluxCarService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CarRouteHandler {

    private final FluxCarService fluxCarService;

    public CarRouteHandler(FluxCarService fluxCarService) {
        this.fluxCarService = fluxCarService;
    }

    public Mono<ServerResponse> allCars(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(fluxCarService.all(), Car.class)
                .doOnError(throwable -> new IllegalStateException("There is an error in your search for all..."));
    }

    public Mono<ServerResponse> carById(ServerRequest serverRequest) {
        String carId = serverRequest.pathVariable("carId");
        return ServerResponse.ok()
                .body(fluxCarService.byId(carId), Car.class)
                .doOnError(throwable -> new IllegalStateException("There is an error in your search by id..."));
    }

    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        String carId = serverRequest.pathVariable("carId");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(fluxCarService.streams(carId), CarEvents.class)
                .doOnError(throwable -> new IllegalStateException("There is an error in your events..."));
    }
}
