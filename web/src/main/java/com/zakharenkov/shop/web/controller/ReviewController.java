package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.Review;
import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.service.service.ProductService;
import com.zakharenkov.shop.service.service.ReviewService;
import com.zakharenkov.shop.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@SessionAttributes({"currentUser", "newReview"})
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/review/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        Review review = reviewService.findById(Long.parseLong(id)).orElse(null);
        if (review != null) {
            reviewService.delete(review);
        }
        return "redirect:/account";
    }

    @GetMapping("/addReview/{id}")
    public String get(Model model, @PathVariable("id") String id) {
        User currentUser = (User) model.asMap().get("currentUser");
        Review review = Review.builder()
                .user(currentUser)
                .product(productService.getById(Long.parseLong(id)).orElse(null))
                .build();
        model.addAttribute("newReview", review);
        System.out.println();
        return "addReview";
    }

    @PostMapping("/addReview")
    public String getForm(Model model, Review review) {
        Review newReview = (Review) model.asMap().get("newReview");
        newReview.setDate(LocalDate.now());
        newReview.setText(review.getText());
        reviewService.save(newReview);
        System.out.println();
        return "redirect:/product/" + newReview.getProduct().getId();
    }



}
