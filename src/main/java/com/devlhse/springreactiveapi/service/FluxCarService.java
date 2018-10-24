package com.devlhse.springreactiveapi.service;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.repository.CarRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FluxCarService {

    private final CarRepository carRepository;

    FluxCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Flux<Car> all () {
        return carRepository.findAll();
    }

    public Mono<Car> byId(String carId) {
        return carRepository.findById(carId);
    }

    public Mono<Car> create(Car car) {
        return carRepository.save(car);
    }
}
