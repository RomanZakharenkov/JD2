package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.dto.FilterDto;
import com.zakharenkov.shop.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductDao extends BaseDao<Long, Product> {

    Long getCountProduct(FilterDto filter);

    List<Product> findByFilter(FilterDto filter);

    Set<String> getAllBrand();
}
