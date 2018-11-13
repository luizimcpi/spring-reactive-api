package com.devlhse.springreactiveapi.endpoint;

import com.devlhse.springreactiveapi.model.Owner;
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
public class OwnerController {

    @Autowired
    private final OwnerService ownerService;


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public Flux<Owner> getAllOwners() {
        return ownerService.all();
    }

    @GetMapping("/owners/{id}")
    public Mono<ResponseEntity<Owner>> getOwnerById(@PathVariable(value = "id") String ownerId) {
        return ownerService.findById(ownerId)
                .map(savedOwner -> ResponseEntity.ok(savedOwner))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/owners")
    public ResponseEntity<Mono<Owner>> createOwner(@Valid @RequestBody Owner ownerRequest) throws URISyntaxException {
        return ResponseEntity.created(new URI("/owners")).body(ownerService.save(ownerRequest));
    }

    @PutMapping("/owners/{id}")
    public Mono<ResponseEntity<Owner>> updateOwner(@PathVariable(value = "id") String ownerId,
                                                   @Valid @RequestBody Owner ownerRequest) {
        return ownerService.findById(ownerId)
                .flatMap(existingOwner -> {
                    existingOwner.setName(ownerRequest.getName());
                    existingOwner.setDocumentNumber(ownerRequest.getDocumentNumber());
                    return ownerService.save(existingOwner);
                })
                .map(updatedOwner -> new ResponseEntity<>(updatedOwner, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/owners/{id}")
    public Mono<ResponseEntity<Void>> deleteOwner(@PathVariable(value = "id") String ownerId) {

        return ownerService.findById(ownerId)
                .flatMap(existingOwner ->
                        ownerService.delete(existingOwner.getId())
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
