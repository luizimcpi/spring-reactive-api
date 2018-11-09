package com.devlhse.springreactiveapi.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.service.FluxOwnerService;

import reactor.core.publisher.Mono;

@Component
public class OwnerRouteHandler {

    private final FluxOwnerService fluxOwnerService;

    public OwnerRouteHandler(FluxOwnerService fluxOwnerService) {
        this.fluxOwnerService = fluxOwnerService;
    }

    public Mono<ServerResponse> allOwners(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(fluxOwnerService.all(), Owner.class)
                .doOnError(throwable -> new IllegalStateException("There is an error in your search for all..."));
    }

    public Mono<ServerResponse> ownerById(ServerRequest serverRequest) {
        String ownerId = serverRequest.pathVariable("ownerId");
        return ServerResponse.ok()
                .body(fluxOwnerService.byId(ownerId), Owner.class)
                .doOnError(throwable -> new IllegalStateException("There is an error in your search by id..."));
    }

    public Mono<ServerResponse> createOwner(ServerRequest req) {

        Mono<Owner> objectMono = req.bodyToMono(Owner.class)
                .flatMap(owner ->  Mono.from(fluxOwnerService.create(owner)));


        return ServerResponse.status(201).body(objectMono, Owner.class);

    }

    Mono<ServerResponse> delete(ServerRequest serverRequest) {

        String ownerId = serverRequest.pathVariable("ownerId");

        Mono<Void> serverResponse = Mono.from(fluxOwnerService.delete(ownerId));

        return ServerResponse.status(204).body(serverResponse, Void.class).switchIfEmpty(ServerResponse.notFound().build());

    }

}
