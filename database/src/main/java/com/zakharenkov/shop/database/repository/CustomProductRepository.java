package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.dto.FilterDto;
import com.zakharenkov.shop.database.model.Product;

import java.util.List;

public interface CustomProductRepository {

    List<Product> findByFilter(FilterDto filter);

    Long getCountProduct(FilterDto filter);
}
