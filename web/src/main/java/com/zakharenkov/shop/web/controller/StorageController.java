package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.model.Storage;
import com.zakharenkov.shop.service.dto.StorageCountDto;
import com.zakharenkov.shop.service.service.ProductService;
import com.zakharenkov.shop.service.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StorageController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}/addStorage")
    public String getPage(Model model, @PathVariable("id") String id) {
        System.out.println();
        StorageCountDto count = StorageCountDto.builder()
                .count(null)
                .build();
        Product product = productService.getById(Long.parseLong(id)).get();
        model.addAttribute("product", product);
        model.addAttribute("count", count);
        return "productAddStorage";
    }

    @PostMapping("/product/{id}/addStorage")
    public String getForm(Model model, @PathVariable("id") String id, StorageCountDto count) {
        System.out.println();
        StorageCountDto storageCountDto = (StorageCountDto) model.asMap().get("storageCountDto");
        Product product = productService.getById(Long.parseLong(id)).get();
        Storage storage = product.getStorage();
        if (storage != null) {
            storage.setCount(storage.getCount() + storageCountDto.getCount());
        } else {
            storage = Storage.builder()
                    .product(product)
                    .count(storageCountDto.getCount())
                    .build();
        }
        storageService.save(storage);
        return "productSuccess";
    }
}
