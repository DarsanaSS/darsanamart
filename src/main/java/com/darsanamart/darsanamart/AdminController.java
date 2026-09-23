package com.darsanamart.darsanamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public AdminController(
            ProductRepository productRepository,
            OrderRepository orderRepository) {

        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
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
            @RequestParam int quantity,
            @RequestParam String category) {

        Product product = new Product(
                name,
                price,
                quantity,
                category
        );

        productRepository.save(product);

        return "redirect:/admin";
    }

    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {

        model.addAttribute("orders", orderRepository.findAll());

        return "admin-orders";
    }

    @GetMapping("/admin/manage-products")
    public String manageProducts(Model model) {

        model.addAttribute("products", productRepository.findAll());

        return "manage-products";
    }

    @GetMapping("/admin/edit-product")
    public String editProductPage(
            @RequestParam Long id,
            Model model) {

        Product product = productRepository.findById(id)
                .orElse(null);

        if (product == null) {
            return "redirect:/admin/manage-products";
        }

        model.addAttribute("product", product);

        return "edit-product";
    }

    @PostMapping("/admin/edit-product")
    public String editProduct(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam int quantity,
            @RequestParam String category) {

        Product product = productRepository.findById(id)
                .orElse(null);

        if (product != null) {

            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setCategory(category);

            productRepository.save(product);
        }

        return "redirect:/admin/manage-products";
    }

    @PostMapping("/admin/delete-product")
    public String deleteProduct(@RequestParam Long id) {

        productRepository.deleteById(id);

        return "redirect:/admin/manage-products";
    }
}