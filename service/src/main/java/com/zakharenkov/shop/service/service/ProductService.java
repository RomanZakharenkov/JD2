package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.dto.FilterDto;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.zakharenkov.shop.database.repository.CustomProductRepositoryImpl.ANY;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Long getCountProduct(FilterDto filter) {
        return productRepository.getCountProduct(filter);
    }

    public List<Product> findByFilter(FilterDto filter) {
        return productRepository.findByFilter(filter);
    }

    public Set<String> getAllBrand() {
        Set<String> allBrand = productRepository.findAllBrand();
        allBrand.add(ANY);
        return allBrand;
    }
}
