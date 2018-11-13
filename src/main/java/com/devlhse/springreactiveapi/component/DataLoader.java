package com.devlhse.springreactiveapi.component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.repository.CarRepository;
import com.devlhse.springreactiveapi.repository.OwnerRepository;

import reactor.core.publisher.Flux;

/*@Component
public class DataLoader implements CommandLineRunner {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    DataLoader(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put( "value", 27000.00 );
        data.put( "color", "red" );
        data.put( "doors", "5" );

        String ownerId = UUID.randomUUID().toString();
        
        ownerRepository.deleteAll()
        .thenMany(
        		Flux.just("Luiz Evangelista")
        		.map(name -> new Owner(ownerId, name, UUID.randomUUID().toString()))
        		.flatMap(ownerRepository::save))
        .subscribe(System.out::println);
        
        
        carRepository.deleteAll()
                .thenMany(
                        Flux.just("Koenigsegg One:1", "Hennessy Venom GT", "Bugatti Veyron Super Sport",  "SSC Ultimate Aero", "McLaren F1", "Pagani Huayra", "Noble M600",
                                "Aston Martin One-77", "Ferrari LaFerrari", "Lamborghini Aventador")
                                .map(model -> new Car(UUID.randomUUID().toString(), ownerId, model, data))
                                .flatMap(carRepository::save))
                .subscribe(System.out::println);
        
    }

}*/
