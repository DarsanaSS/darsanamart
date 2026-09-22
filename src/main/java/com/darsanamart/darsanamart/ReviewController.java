package com.darsanamart.darsanamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewRepository.findAll());
        return "reviews";
    }

    @PostMapping("/reviews/add")
    public String addReview(
            @RequestParam String username,
            @RequestParam String productName,
            @RequestParam int rating,
            @RequestParam String comment) {

        Review review = new Review(
                username,
                productName,
                rating,
                comment
        );

        reviewRepository.save(review);

        return "redirect:/reviews";
    }
}