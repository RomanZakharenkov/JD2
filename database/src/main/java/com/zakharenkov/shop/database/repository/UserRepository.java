package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
