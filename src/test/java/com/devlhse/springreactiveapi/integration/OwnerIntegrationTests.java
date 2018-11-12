package com.devlhse.springreactiveapi.integration;

import com.devlhse.springreactiveapi.model.Owner;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@WebFluxTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OwnerIntegrationTests {

    @Autowired
    private WebTestClient webClient;

    private static Map<String, Owner> ownerMap = new HashMap<>();

    @BeforeClass
    public static void setup() throws Exception {
        ownerMap.put("Luiz Evangelista", new Owner("ee4157c3-a276-4302-9a28-523162b82177", "Luiz Evangelista", "865.306.705-13"));
        ownerMap.put("Andre Faria", new Owner("b085509e-870a-45de-bc9c-dc20778ab4fe", "Andre Faria", "496.712.574-37"));
        ownerMap.put("Lucas Barbosa", new Owner("138ecedc-8fe2-4672-81f7-65bcadf3b800", "Lucas Barbosa", "446.284.824-11"));
    }

    @Test
    public void tc_1_getAllOwners() throws Exception {

        webClient.get().uri("/owners").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Owner.class)
                .hasSize(2)
                .contains(ownerMap.get("Luiz Evangelista"), ownerMap.get("Andre Faria"), ownerMap.get("Lucas Barbosa"));
    }

    @Test
    public void tc_2_getOwner() throws Exception {

        webClient.get().uri("/owners/{ownerId}", "b085509e-870a-45de-bc9c-dc20778ab4fe").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Owner.class)
                .isEqualTo(ownerMap.get("Andre Faria"));
    }

    @Test
    public void tc_3_postOwner() throws Exception {

        webClient.post().uri("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(ownerMap.get("Mary")))
                .exchange()
                .expectStatus().isCreated();
//                .expectBody(String.class)
//                .isEqualTo("Post Successfully!");
    }

    @Test
    public void tc_4_putOwner() throws Exception {

        webClient.put().uri("/owners/{ownerId}", "138ecedc-8fe2-4672-81f7-65bcadf3b800")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(ownerMap.get("Lucas Barbosa")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Owner.class);
//                .isEqualTo(ownerMap.get("Lucas Barbosa"));

    }

    @Test
    public void tc_5_deleteOwner() throws Exception {

        webClient.delete().uri("/owners/{ownerId}", "b085509e-870a-45de-bc9c-dc20778ab4fe")
                .exchange()
                .expectStatus().isAccepted();
//                .expectBody(String.class)
//                .isEqualTo("Delete Succesfully!");
    }
}
