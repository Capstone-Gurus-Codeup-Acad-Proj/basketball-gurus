package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;




@Controller
public class AdminController {

    private final UserRepository userDao;

    public AdminController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/admin")
    public String admin() {
        return "user/admin";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/";
    }

}