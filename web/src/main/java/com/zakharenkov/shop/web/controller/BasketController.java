package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.LineItem;
import com.zakharenkov.shop.database.model.Order;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.zakharenkov.shop.database.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;
import java.util.Set;

@Controller
@SessionAttributes({"currentUser", "basket"})
public class BasketController {

    @Autowired
    private ProductService productService;

    @GetMapping("/basket")
    public String get(Model model) {
        System.out.println();
        return "basket";
    }

    @GetMapping("/basket/add/{id}")
    public String addBasket(Model model, @PathVariable("id") String id) {
        System.out.println();
        Product product = productService.getById(Long.parseLong(id)).orElse(null);
        Order basket = (Order) model.asMap().get("basket");
        LineItem lineItem = LineItem.builder()
                .product(product)
                .count(1)
                .build();
        boolean check = true;
        if (product != null) {
            if (basket.getLineItems().size() > 0) {
                for (LineItem item : basket.getLineItems()) {
                    if (lineItem.getProduct().equals(item.getProduct())) {
                        item.setCount(item.getCount() + 1);
                        check = false;
                    }
                }
            }
            if (check) {
                basket.getLineItems().add(lineItem);
            }
        }

        return "redirect:/basket";
    }
}
