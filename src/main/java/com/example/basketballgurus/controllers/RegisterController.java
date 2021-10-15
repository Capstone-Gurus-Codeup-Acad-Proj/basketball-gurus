package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private final UserRepository userDao;

    public RegisterController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }
    @PostMapping("/register")
    public String create(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/";
    }
}
