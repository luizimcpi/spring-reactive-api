package com.devlhse.springreactiveapi.endpoint;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.service.CarService;
import com.devlhse.springreactiveapi.service.OwnerService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CarRouteHandler {

    private final OwnerService fluxOwnerService;
    private final CarService fluxCarService;
    private final Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public CarRouteHandler(OwnerService fluxOwnerService, CarService fluxCarService) {
        this.fluxOwnerService = fluxOwnerService;
        this.fluxCarService = fluxCarService;
    }

    public Mono<ServerResponse> allCars(ServerRequest serverRequest) {
        String ownerId = serverRequest.pathVariable("ownerId");

        return ServerResponse.ok()
                .body(fluxCarService.allByOwnerId(ownerId), Car.class)
                .doOnError(throwable -> new IllegalStateException("There is an error in your search for all..."));
    }

    public Mono<ServerResponse> carById(ServerRequest serverRequest) {
        String ownerId = serverRequest.pathVariable("ownerId");
        String carId = serverRequest.pathVariable("carId");

        return ServerResponse.ok()
                .body(fluxCarService.byOwnerIdAndCarId(ownerId, carId), Car.class)
                .switchIfEmpty(notFound)
                .doOnError(throwable -> new IllegalStateException("There is an error in your search by id..."));
    }

    public Mono<ServerResponse> createCar(ServerRequest serverRequest) {

        String ownerId = serverRequest.pathVariable("ownerId");


        //TODO Corrigir pois esta permitindo inserção de carros com owners inexistentes
        fluxOwnerService.findById(ownerId).doOnError(throwable -> new IllegalStateException("There is an error in your search by customerID..."));


        Mono<Car> objectMono = serverRequest.bodyToMono(Car.class)
                .flatMap(car -> Mono.from(fluxCarService.create(car, ownerId)));

        return ServerResponse.status(201).body(objectMono, Car.class);
    }

    Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String ownerId = serverRequest.pathVariable("ownerId");
        String carId = serverRequest.pathVariable("carId");

        Mono<Void> serverResponse = Mono.from(fluxCarService.deleteCarById(carId));

        return ServerResponse.status(204).body(serverResponse, Void.class).switchIfEmpty(notFound);

    }

}
