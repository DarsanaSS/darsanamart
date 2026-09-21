package com.darsanamart.darsanamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final ProductRepository productRepository;

    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {

        model.addAttribute("products", productRepository.findAll());

        return "admin";
    }

    @GetMapping("/admin/add-product")
    public String addProductPage() {

        return "add-product";
    }

    @PostMapping("/admin/add-product")
    public String addProduct(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam int quantity) {

        Product product = new Product(name, price, quantity);

        productRepository.save(product);

        return "redirect:/admin";
    }
}