package com.zakharenkov.shop.web;

import com.zakharenkov.shop.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class ProductServlet {

    @Autowired
    private ProductService productService;
}
