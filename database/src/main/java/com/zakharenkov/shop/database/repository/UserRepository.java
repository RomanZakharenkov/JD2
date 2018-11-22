package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


}
