package com.zakharenkov.shop.service;

import com.zakharenkov.shop.dao.ProductDaoImpl;
import com.zakharenkov.shop.dto.FilterDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.zakharenkov.shop.model.Product;

import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private static final ProductService INSTANCE = new ProductService();

    public List<Product> findAll() {
        return ProductDaoImpl.getInstance().findAll();
    }

    public Long getCountProduct(FilterDto filter) {
        return ProductDaoImpl.getInstance().getCountProduct(filter);
    }

    public List<Product> findByFilter(FilterDto filter) {
        return ProductDaoImpl.getInstance().findByFilter(filter);
    }

    public Set<String> getAllBrand() {
        return ProductDaoImpl.getInstance().getAllBrand();
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}
