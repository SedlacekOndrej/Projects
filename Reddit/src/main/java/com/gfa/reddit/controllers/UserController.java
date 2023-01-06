package com.gfa.reddit.controllers;

import com.gfa.reddit.models.User;
import com.gfa.reddit.repositories.UserRepository;
import com.gfa.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String renderRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String renderLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password,
                            Model model) {
        return userService.login(username, password, model);
    }

    @GetMapping("/logout")
    public String logoutUser() {
        User.setIsLoginValid(false);
        return "redirect:/";
    }
}
