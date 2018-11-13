package com.devlhse.springreactiveapi.integration;

import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.repository.OwnerRepository;
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

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OwnerIntegrationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private OwnerRepository ownerRepository;


    private final String ownerId = "valid_test_owner_id";


    @Test
    public void test_01_shouldSaveOwner() {
        Owner owner = new Owner(ownerId, "Luiz Evangelista", "XXX.XXX.XXX-XX");

        webTestClient.post().uri("/owners")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(owner), Owner.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().valueEquals("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString())
                .expectBody()
                .jsonPath("$.name").isEqualTo("Luiz Evangelista")
                .jsonPath("$.documentNumber").isEqualTo("XXX.XXX.XXX-XX");

    }

    @Test
    public void test_02_shouldGetListOfOwners() {
        webTestClient.get().uri("/owners")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString())
                .expectBodyList(Owner.class).hasSize(1);
    }

    @Test
    public void test_03_shouldGetOwnerById() {

        webTestClient.get().uri("/owners/{id}", ownerId).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void test_04_shouldUpdateOwnerById() {

        Owner ownerRequest = new Owner();
        ownerRequest.setName("Luiz Henrique Evangelista");
        ownerRequest.setDocumentNumber("942.881.123-89");

        webTestClient.put()
                .uri("/owners/{id}", Collections.singletonMap("id", ownerId))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(ownerRequest), Owner.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isEqualTo(ownerId)
                .jsonPath("$.name").isEqualTo("Luiz Henrique Evangelista")
                .jsonPath("$.documentNumber").isEqualTo("942.881.123-89");
    }


    @Test
    public void test_05_shouldDeleteOwnerById() {

        webTestClient.delete().uri("/owners/{ownerId}", ownerId).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }


}
