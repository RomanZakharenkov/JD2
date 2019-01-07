package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.service.dto.PasswordDto;
import com.zakharenkov.shop.service.dto.UserRegistrationDto;
import com.zakharenkov.shop.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"currentUser", "filter"})
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String getPage(Model model) {
        User currentUser = (User) model.asMap().get("currentUser");
        model.addAttribute("currentUser", userService.findUserById(currentUser.getId()).get());
        System.out.println();
        return "account";
    }

    @GetMapping("/account/edit")
    public String getEditPage(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("newUser", user);
        return "accountEdit";
    }

    @PostMapping("/account/edit")
    public String getForm(Model model, UserRegistrationDto user) {
        User currentUser = (User) model.asMap().get("currentUser");
        currentUser.setEmail(user.getEmail());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setNumber(user.getNumber());
        userService.update(currentUser);
        return "accountSuccess";
    }

    @GetMapping("/account/password")
    public String getPageChangePassword(Model model) {
        PasswordDto passwordDto = PasswordDto.builder().build();
        model.addAttribute("password", passwordDto);
        return "accountChangePassword";
    }

    @PostMapping("/account/password")
    public String getFormPassword(Model model, PasswordDto passwordDto) {
        System.out.println();
        User currentUser = (User) model.asMap().get("currentUser");
        if (passwordDto.getNewPassword().equals(passwordDto.getNewPasswordRepeat()) && currentUser.getPassword().equals(passwordDto.getOldPassword())) {
            currentUser.setPassword(passwordDto.getNewPassword());
            currentUser = userService.update(currentUser);
            return "accountSuccess";
        } else {
            return "redirect:/account/password";
        }
    }
}
