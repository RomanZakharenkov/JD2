package com.zakharenkov.shop.database.dao;

import com.zakharenkov.shop.database.dto.FilterDto;
import com.zakharenkov.shop.database.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductDao extends BaseDao<Long, Product> {

    Long getCountProduct(FilterDto filter);

    List<Product> findByFilter(FilterDto filter);

    Set<String> getAllBrand();
}
