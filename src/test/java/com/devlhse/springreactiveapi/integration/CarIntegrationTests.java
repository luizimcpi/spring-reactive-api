package com.devlhse.springreactiveapi.integration;

import com.devlhse.springreactiveapi.model.Car;
import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.repository.CarRepository;
import com.devlhse.springreactiveapi.repository.OwnerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarIntegrationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;


    private final String VALID_OWNER_ID = "valid_test_owner_id";
    private final String INVALID_OWNER_ID = "invalid_test_owner_id";
    private final String VALID_CAR_ID = "valid_test_car_id";

    @Before
    public void setUp(){
        Owner owner = new Owner(VALID_OWNER_ID, "Luiz Evangelista", "XXX.XXX.XXX-XX");
        ownerRepository.save(owner).subscribe();
    }


    @Test
    public void test_01_shouldSaveCar() {
        Map<String, Object> data = new HashMap<>();
        data.put("doors", "5");
        Car car = new Car(VALID_CAR_ID, VALID_OWNER_ID, "Volkswagen UP MSI", data);

        webTestClient.post().uri("/owners/{ownerId}/cars", VALID_OWNER_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(car), Car.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().valueEquals("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString())
                .expectBody()
                .jsonPath("$.model").isEqualTo("Volkswagen UP MSI")
                .jsonPath("$.data.doors").isEqualTo("5");

    }

    @Test
    public void test_02_shouldNotSaveCarWhenInvalidOwner() {
        Map<String, Object> data = new HashMap<>();
        data.put("doors", "5");
        Car car = new Car(VALID_CAR_ID, VALID_OWNER_ID, "Volkswagen UP MSI", data);

        webTestClient.post().uri("/owners/{ownerId}/cars", INVALID_OWNER_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(car), Car.class)
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    public void test_03_shouldGetListOfOwnerCars() {
        webTestClient.get().uri("/owners/{ownerId}/cars", VALID_OWNER_ID)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString())
                .expectBodyList(Owner.class).hasSize(1);
    }

    @Test
    public void test_04_shouldGetCarByIdAndOwnerId() {

        webTestClient.get().uri("/owners/{ownerId}/cars/{carId}", VALID_OWNER_ID, VALID_CAR_ID).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void test_05_shouldReturnNotFoundWhenInvalidCarIdOrOwnerId() {

        webTestClient.get().uri("/owners/{ownerId}/cars/{carId}", INVALID_OWNER_ID, VALID_CAR_ID).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody();
    }

    @Test
    public void test_06_shouldUpdateCarById() {

        Car carRequest = new Car();
        carRequest.setModel("Volkswagen UP! TSI");

        webTestClient.put()
                .uri("/owners/{ownerId}/cars/{carId}",  VALID_OWNER_ID, VALID_CAR_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(carRequest), Car.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isEqualTo(VALID_CAR_ID)
                .jsonPath("$.model").isEqualTo("Volkswagen UP! TSI");
    }

    @Test
    public void test_07_shouldDeleteCarById() {

        webTestClient.delete().uri("/owners/{ownerId}/cars/{carId}", VALID_OWNER_ID, VALID_CAR_ID).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

    @After
    public void clear(){
        ownerRepository.deleteById(VALID_OWNER_ID).subscribe();
    }

}
