package com.darsanamart.darsanamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderHistoryController {

    private final OrderRepository orderRepository;

    public OrderHistoryController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/order-history")
    public String orderHistory(
            HttpSession session,
            Model model) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/";
        }

        List<Order> orders = orderRepository.findAll()
                .stream()
                .filter(order ->
                        username.equals(order.getUsername()))
                .toList();

        model.addAttribute("orders", orders);
        model.addAttribute("username", username);

        return "order-history";
    }
}