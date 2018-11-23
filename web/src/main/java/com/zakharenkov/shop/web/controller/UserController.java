package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(Model model,
                          @RequestParam("id") String paramId) {

        Long id = Long.parseLong(paramId);
        Optional<User> user = userService.findUserById(id);
        user.ifPresent(user1 -> model.addAttribute("user", user1));
        return "getUser";
    }
}
