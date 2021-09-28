package com.example;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

@Repository
public interface UserRepository extends PageableRepository<User, Long> {}
