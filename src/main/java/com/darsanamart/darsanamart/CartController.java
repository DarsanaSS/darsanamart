package com.darsanamart.darsanamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartItems", cartRepository.findAll());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam String productName,
            @RequestParam double price,
            @RequestParam int quantity) {

        Cart cart = new Cart(
                productName,
                price,
                quantity
        );

        cartRepository.save(cart);

        return "redirect:/cart";
    }
}