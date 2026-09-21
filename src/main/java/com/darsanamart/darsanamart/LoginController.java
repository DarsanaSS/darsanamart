package com.darsanamart.darsanamart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String login(@RequestBody User user) {

        User existingUser = userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(user.getUsername())
                        && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null);

        if (existingUser != null) {
            return "Login successful";
        }

        return "Invalid username or password";
    }
}