package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getPage() {
        return "login";
    }

    //  TODO: додумкать логинацию
    @GetMapping("/start")
    public String startPage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        Optional<User> userByEmail = userService.findUserByEmail(userName);


        if (userByEmail.isPresent()) {
            model.addAttribute("currentUser", userByEmail.get());
            return "redirect:/products";
        } else {
            return "redirect:/login?error";
        }
    }
}
