package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
