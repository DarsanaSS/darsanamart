package com.darsanamart.darsanamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String products(
            @RequestParam(required = false) String search,
            Model model) {

        List<Product> products;

        if (search == null || search.trim().isEmpty()) {
            products = productRepository.findAll();
        } else {
            String searchText = search.trim().toLowerCase();

            products = productRepository.findAll()
                    .stream()
                    .filter(product ->
                            product.getName()
                                    .toLowerCase()
                                    .contains(searchText))
                    .toList();
        }

        model.addAttribute("products", products);
        model.addAttribute("search", search);

        return "products";
    }
}