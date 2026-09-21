package com.darsanamart.darsanamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderController(
            CartRepository cartRepository,
            OrderRepository orderRepository) {

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {

        var cartItems = cartRepository.findAll();

        double total = 0;

        for (Cart item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/order/place")
    public String placeOrder() {

        var cartItems = cartRepository.findAll();

        double total = 0;

        for (Cart item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        Order order = new Order(total);
        orderRepository.save(order);

        cartRepository.deleteAll();

        return "redirect:/order-success";
    }

    @GetMapping("/order-success")
    public String orderSuccess() {
        return "order-success";
    }
}