package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.service.service.ProductService;
import com.zakharenkov.shop.service.service.UserService;
import com.zakharenkov.shop.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;
import java.util.Set;

@Controller
@SessionAttributes("currentUser")
public class FavoriteController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/favorite/delete/{product_id}")
    public String delete(Model model, @PathVariable("product_id") String productId) {
        Product product = productService.getById(Long.parseLong(productId)).get();
        User currentUser = (User) model.asMap().get("currentUser");
        currentUser.getProducts().remove(product);
        userService.update(currentUser);
        System.out.println();
        return "redirect:/account";
    }

    @GetMapping("/favorite/add/{product_id}")
    public String add(Model model, @PathVariable("product_id") String productId) {
        Optional<Product> optionalProduct = productService.getById(Long.parseLong(productId));
        User user = (User) model.asMap().get("currentUser");
        if (optionalProduct.isPresent()) {
            user.getProducts().add(optionalProduct.get());
            userService.update(user);
        }
        System.out.println();
        return "redirect:/account";
    }
}
