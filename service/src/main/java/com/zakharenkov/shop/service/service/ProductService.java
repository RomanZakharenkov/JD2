package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.dao.ProductDaoImpl;
import com.zakharenkov.shop.database.dto.FilterDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.zakharenkov.shop.database.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDaoImpl productDao;

    //    public List<Product> findAll() {
//        return ProductDaoImpl.getInstance().findAll();
//    }
//
//    public Long getCountProduct(FilterDto filter) {
//        return ProductDaoImpl.getInstance().getCountProduct(filter);
//    }
//
//    public List<Product> findByFilter(FilterDto filter) {
//        return ProductDaoImpl.getInstance().findByFilter(filter);
//    }
//
    public Set<String> getAllBrand() {
        return productDao.getAllBrand();
    }

}
