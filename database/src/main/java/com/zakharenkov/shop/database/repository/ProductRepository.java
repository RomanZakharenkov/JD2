package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long>, CustomProductRepository {

    @Query("select p.productDetail.brand from Product p group by p.productDetail.brand")
    Set<String> findAllBrand();

}
