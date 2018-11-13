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

import java.util.UUID;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OwnerIntegrationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private OwnerRepository ownerRepository;


    private final String ownerId = UUID.randomUUID().toString();


    @Test
    public void a_shouldSaveOwner() {
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
    public void b_shouldGetListOfOwners() {
        webTestClient.get().uri("/owners")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString())
                .expectBodyList(Owner.class).hasSize(2);
    }

    @Test
    public void c_shouldGetOwnerById() {

        webTestClient.get().uri("/owners/{ownerId}", ownerId).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
//                .jsonPath("$.name").isEqualTo("Luiz Evangelista")
//                .jsonPath("$.documentNumber").isEqualTo("XXX.XXX.XXX-XX");
    }

    @Test
    public void d_shouldDeleteOwnerById() {

        webTestClient.delete().uri("/owners/{ownerId}", ownerId).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }


}
