package com.darsanamart.darsanamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage2() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String usernameOrEmail,
            @RequestParam String password,
            HttpSession session) {

        User foundUser = userRepository.findAll().stream()
                .filter(user ->
                        (usernameOrEmail.equals(user.getUsername())
                        || usernameOrEmail.equals(user.getEmail()))
                        && password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);

        if (foundUser == null) {
            return "redirect:/";
        }

        session.setAttribute("username", foundUser.getUsername());

        if ("SELLER".equals(foundUser.getRole())) {
            return "redirect:/admin";
        }

        if ("BUYER".equals(foundUser.getRole())) {
            return "redirect:/products";
        }

        return "redirect:/";
    }
}