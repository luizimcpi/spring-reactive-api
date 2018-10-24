package com.devlhse.springreactiveapi.component;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    private final CarRepository carRepository;

    DataLoader(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put( "value", 27000.00 );
        data.put( "color", "red" );
        data.put( "doors", "5" );

        carRepository.deleteAll()
                .thenMany(
                        Flux.just("Koenigsegg One:1", "Hennessy Venom GT", "Bugatti Veyron Super Sport",  "SSC Ultimate Aero", "McLaren F1", "Pagani Huayra", "Noble M600",
                                "Aston Martin One-77", "Ferrari LaFerrari", "Lamborghini Aventador")
                                .map(model -> new Car(UUID.randomUUID().toString(), model, data))
                                .flatMap(carRepository::save))
                .subscribe(System.out::println);
    }

}
