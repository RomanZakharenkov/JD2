package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.dto.FilterDto;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.repository.ProductRepository;
import com.zakharenkov.shop.service.configuration.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static com.zakharenkov.shop.database.repository.CustomProductRepositoryImpl.ANY;

@Service
@CacheConfig(cacheNames = "products")
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

    @CacheEvict
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Cacheable()
    public List<Product> findByFilter(FilterDto filter) {
        return productRepository.findByFilter(filter);
    }

    public Set<String> getAllBrand() {
        Set<String> allBrand = productRepository.findAllBrand();
        allBrand.add(ANY);
        return allBrand;
    }


    @Cacheable()
    public Optional<Product> getById(Long id) {
        Logger LOG = Logger.getLogger("a");
        LOG.info("+66+6+6+6++6+6+6+6+6+6++6+6+6+6");
        return productRepository.findById(id);
    }
}
