package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

    Optional<Category> findByName(String name);
}
