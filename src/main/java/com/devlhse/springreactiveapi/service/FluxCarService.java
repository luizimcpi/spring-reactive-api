package com.devlhse.springreactiveapi.service;

import org.springframework.stereotype.Service;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.repository.CarRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxCarService {

    private final CarRepository carRepository;

    FluxCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Flux<Car> allByOwnerId (String ownerId) {
        return carRepository.findAllByOwnerId(ownerId);
    }

    public Mono<Car> byOwnerIdAndCarId(String ownerId, String carId) {
        return carRepository.findByOwnerIdAndId(ownerId, carId);
    }

    public Mono<Car> create(Car car, String ownerId) {
    	car.setOwnerId(ownerId);
        return carRepository.save(car);
    }

    public Mono<Void> deleteCarById(String ownerId, String carId) {
    	  Mono<Car> car = carRepository.findByOwnerIdAndId(ownerId, carId);
    	  return carRepository.deleteById(carId);
    }

}
