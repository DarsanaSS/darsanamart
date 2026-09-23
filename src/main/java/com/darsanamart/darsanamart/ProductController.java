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
            @RequestParam(required = false) String category,
            Model model) {

        List<Product> products = productRepository.findAll();

        if (search != null && !search.trim().isEmpty()) {

            String searchText = search.trim().toLowerCase();

            products = products.stream()
                    .filter(product ->
                            product.getName()
                                    .toLowerCase()
                                    .contains(searchText))
                    .toList();
        }

        if (category != null && !category.trim().isEmpty()) {

            String categoryText = category.trim().toLowerCase();

            products = products.stream()
                    .filter(product ->
                            product.getCategory() != null &&
                            product.getCategory()
                                    .toLowerCase()
                                    .equals(categoryText))
                    .toList();
        }

        model.addAttribute("products", products);
        model.addAttribute("search", search);
        model.addAttribute("category", category);

        return "products";
    }
}