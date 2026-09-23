package com.darsanamart.darsanamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderController(
            OrderRepository orderRepository,
            CartRepository cartRepository) {

        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping("/checkout")
    public String checkout(
            HttpSession session,
            Model model) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/";
        }

        List<Cart> cartItems =
                cartRepository.findByUsername(username);

        double total = 0;

        for (Cart item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("username", username);

        return "checkout";
    }

    @PostMapping("/order/place")
    public String placeOrder(
            HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/";
        }

        List<Cart> cartItems =
                cartRepository.findByUsername(username);

        for (Cart item : cartItems) {

            double itemTotal =
                    item.getPrice() * item.getQuantity();

            Order order = new Order(
                    username,
                    item.getProductId(),
                    item.getProductName(),
                    item.getQuantity(),
                    item.getPrice(),
                    itemTotal,
                    "Pending"
            );

            orderRepository.save(order);
        }

        cartRepository.deleteAll(cartItems);

        return "redirect:/order-success";
    }

    @GetMapping("/order-success")
    public String orderSuccess() {

        return "order-success";
    }
}