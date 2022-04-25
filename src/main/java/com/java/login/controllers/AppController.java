package com.java.login.controllers;

import com.java.login.domain.User;
import com.java.login.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String process_register(User user) {
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        String decodePass = cryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(decodePass);
        userRepository.save(user);
        return "register_success";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

}
