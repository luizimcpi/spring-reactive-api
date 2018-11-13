package com.devlhse.springreactiveapi.service;

import org.springframework.stereotype.Service;

import com.devlhse.springreactiveapi.model.Owner;
import com.devlhse.springreactiveapi.repository.OwnerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Flux<Owner> all () {
        return ownerRepository.findAll();
    }

    public Mono<Owner> findById(String ownerId) {
        return ownerRepository.findById(ownerId);
    }

    public Mono<Owner> save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Mono<Void> delete(String id) {
        return ownerRepository.deleteById(id);
    }

}
