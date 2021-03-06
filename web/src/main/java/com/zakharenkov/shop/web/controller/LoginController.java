package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.LineItem;
import com.zakharenkov.shop.database.model.Order;
import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.service.dto.UserRegistrationDto;
import com.zakharenkov.shop.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@SessionAttributes({"currentUser", "basket"})
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getPage() {
        return "login";
    }

    @GetMapping("/start")
    public String startPage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        Optional<User> userByEmail = userService.findUserByEmail(userName);

        if (userByEmail.isPresent()) {
            User user = userByEmail.get();
            Set<LineItem> lineItems = new HashSet<>();
            Order order = Order.builder()
                    .user(user)
                    .lineItems(lineItems)
                    .build();
            model.addAttribute("currentUser", user);
            model.addAttribute("basket", order);
            return "redirect:/products";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("newUser", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String getFormRegistrationPage(Model model, UserRegistrationDto user) {
//        TODO: Дописать валидацию на введеные поля
        userService.saveUser(user);

        return "redirect:/login";
    }
}
