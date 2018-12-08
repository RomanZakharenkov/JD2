package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.service.service.ProductService;
import com.zakharenkov.shop.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class ProductInfoController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String get(Model model, @PathVariable("id") String id) {
        model.addAttribute("product", productService.getById(Long.parseLong(id)).orElse(null));
        return "product";
    }

}
