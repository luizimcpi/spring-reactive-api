package com.devlhse.springreactiveapi.endpoint;

import com.devlhse.springreactiveapi.dto.request.OwnerRequest;
import com.devlhse.springreactiveapi.dto.response.OwnerResponse;
import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.service.OwnerService;
import com.devlhse.springreactiveapi.utils.OwnerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@RestController
public class OwnerController {

    @Autowired
    private final OwnerService ownerService;


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public Mono<ResponseEntity<List<OwnerResponse>>> getAllOwners() {
        return ownerService.all()
                .map(OwnerUtils::toResponse)
                .collectList()
                .map(list -> list.isEmpty() ?  ResponseEntity.noContent().build() : ResponseEntity.ok(list));
    }

    @GetMapping("/owners/{id}")
    public Mono<ResponseEntity<OwnerResponse>> getOwnerById(@PathVariable(value = "id") String ownerId) {
        return ownerService.findById(ownerId)
                .map(OwnerUtils::toResponse)
                .map(ownerResponse -> ResponseEntity.ok(ownerResponse))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/owners", consumes = "application/json")
    public Mono<ResponseEntity<OwnerResponse>> createOwner(@Valid @RequestBody OwnerRequest ownerRequest){
        Owner owner = OwnerUtils.toModel(ownerRequest);
        return ownerService.save(owner)
                .map(OwnerUtils::toResponse)
                .map(ownerResponse -> ResponseEntity.created(URI.create("/owner/"+ownerResponse.getId()))
                        .body(ownerResponse));
    }

    @PutMapping(path = "/owners/{id}", consumes = "application/json")
    public Mono<ResponseEntity<OwnerResponse>> updateOwner(@PathVariable(value = "id") String ownerId,
                                                   @Valid @RequestBody OwnerRequest ownerRequest) {
        return ownerService.findById(ownerId)
                .flatMap(existingOwner -> {
                    existingOwner.setName(ownerRequest.getName());
                    existingOwner.setDocumentNumber(ownerRequest.getDocumentNumber());
                    existingOwner.setUpdatedAt(new Date());
                    return ownerService.save(existingOwner);
                })
                .map(OwnerUtils::toResponse)
                .map(updatedOwner -> ResponseEntity.ok(updatedOwner))
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
