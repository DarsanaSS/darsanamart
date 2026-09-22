package com.darsanamart.darsanamart;

import jakarta.servlet.http.HttpSession;
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
            @RequestParam Long productId,
            @RequestParam String productName,
            @RequestParam double price,
            @RequestParam int quantity,
            HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/";
        }

        Cart existingCart = cartRepository
                .findByUsernameAndProductName(username, productName)
                .orElse(null);

        if (existingCart != null) {

            existingCart.setQuantity(
                    existingCart.getQuantity() + quantity
            );

            cartRepository.save(existingCart);

        } else {

            Cart cart = new Cart(
                    username,
                    productId,
                    productName,
                    price,
                    quantity
            );

            cartRepository.save(cart);
        }

        // Stay on Products page
        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/";
        }

        var cartItems = cartRepository.findByUsername(username);

        double total = 0;

        for (Cart item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("username", username);

        return "cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(
            @PathVariable Long id,
            HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/";
        }

        cartRepository.findByIdAndUsername(id, username)
                .ifPresent(cartRepository::delete);

        return "redirect:/cart";
    }
}