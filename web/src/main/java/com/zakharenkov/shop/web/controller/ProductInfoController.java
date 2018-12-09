package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.Category;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.model.ProductDetail;
import com.zakharenkov.shop.service.dto.ProductDto;
import com.zakharenkov.shop.service.service.CategoryService;
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

import java.util.Optional;

@Controller
@SessionAttributes("currentUser")
public class ProductInfoController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product/{id}")
    public String get(Model model, @PathVariable("id") String id) {
        model.addAttribute("product", productService.getById(Long.parseLong(id)).orElse(null));
        return "product";
    }

    @GetMapping("product/{id}/edit")
    public String getEditPage(Model model, @PathVariable("id") String id) {
        Product product = productService.getById(Long.parseLong(id)).get();
        ProductDto productDto = ProductDto.builder()
                .brand(product.getProductDetail().getBrand())
                .model(product.getProductDetail().getModel())
                .description(product.getProductDetail().getDescription())
                .image(product.getProductDetail().getImage())
                .category(product.getCategory().getName())
                .price(product.getPrice())
                .build();

        model.addAttribute("product", product);
        model.addAttribute("productDto", productDto);

        return "productEdit";
    }

    @PostMapping("/product/{id}/edit")
    public String getForm(Model model, @PathVariable("id") String id, ProductDto productDto) {
        System.out.println();
        Product product = productService.getById(Long.parseLong(id)).get();
        ProductDetail productDetail = ProductDetail.builder()
                .brand(productDto.getBrand())
                .model(productDto.getModel())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .build();
        product.setProductDetail(productDetail);
        product.setPrice(productDto.getPrice());

        Optional<Category> categoryOptional = categoryService.getByName(productDto.getCategory());
        Category category;

        if (categoryOptional.isPresent()) {
            category = categoryOptional.get();
        } else {
            category = Category.builder()
                    .name(productDto.getCategory())
                    .build();
            category = categoryService.save(category);
        }
        product.setCategory(category);

        productService.update(product);

        return "redirect:/product/" + id;
    }
}
