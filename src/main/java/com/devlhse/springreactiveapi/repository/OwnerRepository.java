package com.devlhse.springreactiveapi.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.devlhse.springreactiveapi.model.Owner;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends ReactiveMongoRepository<Owner, String> {
}
