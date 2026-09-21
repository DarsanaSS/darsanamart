package com.darsanamart.darsanamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam String productName,
            @RequestParam double price,
            @RequestParam int quantity) {

        Cart cart = new Cart(productName, price, quantity);
        cartRepository.save(cart);

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model) {

        var cartItems = cartRepository.findAll();

        double total = 0;

        for (Cart item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {

        cartRepository.deleteById(id);

        return "redirect:/cart";
    }
}