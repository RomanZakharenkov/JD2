package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.database.repository.UserRepository;
import com.zakharenkov.shop.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(Model model) {
        Optional<User> user = userService.findUserById(1L);
        System.out.println(user.get());
        model.addAttribute("user", user.get());
        return "getUser";
    }
}
