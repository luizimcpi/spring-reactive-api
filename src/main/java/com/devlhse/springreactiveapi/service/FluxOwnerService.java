package com.devlhse.springreactiveapi.service;

import org.springframework.stereotype.Service;

import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.repository.OwnerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxOwnerService {

    private final OwnerRepository ownerRepository;

    FluxOwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Flux<Owner> all () {
        return ownerRepository.findAll();
    }

    public Mono<Owner> byId(String ownerId) {
        return ownerRepository.findById(ownerId);
    }

    public Mono<Boolean> existsById(String ownerId){
        return ownerRepository.existsById(ownerId);
    }

    public Mono<Owner> create(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Mono<Void> delete(String id) {
        return ownerRepository.deleteById(id);
    }

}
