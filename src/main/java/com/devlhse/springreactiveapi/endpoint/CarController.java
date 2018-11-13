package com.devlhse.springreactiveapi.endpoint;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.service.CarService;
import com.devlhse.springreactiveapi.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CarController {

    @Autowired
    private final CarService carService;

    @Autowired
    private final OwnerService ownerService;


    public CarController(CarService carService, OwnerService ownerService) {
        this.carService = carService;
        this.ownerService = ownerService;
    }

    @GetMapping("/owners/{ownerId}/cars")
    public Flux<Car> getAllCars(@PathVariable(value = "ownerId") String ownerId) {
        return carService.findAllByOwnerId(ownerId);
    }

    @GetMapping("/owners/{ownerId}/cars/{carId}")
    public Mono<ResponseEntity<Car>> getCarById(@PathVariable(value = "ownerId") String ownerId,
                                                    @PathVariable(value = "carId") String carId) {
        return carService.findByOwnerIdAndCarId(ownerId, carId)
                .map(savedCar -> ResponseEntity.ok(savedCar))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/owners/{ownerId}/cars")
    public Mono<ResponseEntity<Car>> createCar(@PathVariable(value = "ownerId") String ownerId,
                                                   @Valid @RequestBody Car carRequest) throws URISyntaxException {
        return ownerService.findById(ownerId)
                .flatMap(existingOwner -> {
                    return carService.save(carRequest, ownerId);
                })
                .map(savedCar -> new ResponseEntity<>(savedCar, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/owners/{ownerId}/cars/{carId}")
    public Mono<ResponseEntity<Car>> updateCar(@PathVariable(value = "ownerId") String ownerId,
                                                   @PathVariable(value = "carId") String carId,
                                                   @Valid @RequestBody Car carRequest) {
        return carService.findByOwnerIdAndCarId(ownerId, carId)
                .flatMap(existingCar -> {
                    existingCar.setModel(carRequest.getModel());
                    existingCar.setData(carRequest.getData());
                    return carService.save(existingCar, ownerId);
                })
                .map(updatedCar -> new ResponseEntity<>(updatedCar, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/owners/{ownerId}/cars/{carId}")
    public Mono<ResponseEntity<Void>> deleteOwner(@PathVariable(value = "ownerId") String ownerId,
                                                  @PathVariable(value = "carId") String carId) {

        return carService.findByOwnerIdAndCarId(ownerId, carId)
                .flatMap(existingCar ->
                        carService.deleteCarById(existingCar.getId())
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
