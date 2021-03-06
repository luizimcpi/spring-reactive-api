package com.devlhse.springreactiveapi.integration;

import com.devlhse.springreactiveapi.dto.request.OwnerRequest;
import com.devlhse.springreactiveapi.dto.response.OwnerResponse;
import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.repository.OwnerRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class OwnerIntegrationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private OwnerRepository ownerRepository;


    private final String VALID_OWNER_ID = "valid_test_owner_id";


    @Test
    public void test_01_shouldSaveOwner() {
        OwnerRequest request = new OwnerRequest();
        request.setName("Luiz Evangelista");
        request.setDocumentNumber("XXX.XXX.XXX-XX");

        webTestClient.post().uri("/owners")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(request), OwnerRequest.class)
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
                .expectBodyList(OwnerResponse.class).hasSize(1);
    }

    @Test
    public void test_03_shouldGetOwnerById() {

        webTestClient.get().uri("/owners/{id}", VALID_OWNER_ID).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void test_04_shouldUpdateOwnerById() {

        OwnerRequest ownerRequest = new OwnerRequest();
        ownerRequest.setName("Luiz Henrique Evangelista");
        ownerRequest.setDocumentNumber("942.881.123-89");

        webTestClient.put()
                .uri("/owners/{id}", Collections.singletonMap("id", VALID_OWNER_ID))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(ownerRequest), OwnerRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isEqualTo(VALID_OWNER_ID)
                .jsonPath("$.name").isEqualTo("Luiz Henrique Evangelista")
                .jsonPath("$.documentNumber").isEqualTo("942.881.123-89");
    }


    @Test
    public void test_05_shouldDeleteOwnerById() {

        webTestClient.delete().uri("/owners/{ownerId}", VALID_OWNER_ID).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }


}
