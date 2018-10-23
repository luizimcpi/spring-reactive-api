package com.devlhse.springreactiveapi.repository;

import com.devlhse.springreactiveapi.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {
}
