package com.devlhse.springreactiveapi.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.devlhse.springreactiveapi.model.Car;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {
	Flux<Car> findAllByOwnerId(String ownerId);
	Mono<Car> findByOwnerIdAndId(String ownerId, String id);
}
