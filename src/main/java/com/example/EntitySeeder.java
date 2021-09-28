package com.example;

import io.micronaut.context.annotation.Context;

@Context
public class EntitySeeder {

    public final UserRepository userRepository;

    public EntitySeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
        saveSomeEntities();
    }

    private void saveSomeEntities() {
        ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").chars().
                forEach(c ->
                        userRepository.save(new User(Character.toString((char) c)))
                );
    }
}
