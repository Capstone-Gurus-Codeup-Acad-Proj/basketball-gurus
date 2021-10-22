package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
<<<<<<< HEAD
import com.example.basketballgurus.repos.UserRepository;
=======
>>>>>>> 31761165d10e18dfdc41393b437a2a399b3b00d8
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdminController {

    private final UserRepository userDao;

    public AdminController(UserRepository userDao) {
        this.userDao = userDao;
    }
    @GetMapping("/admin")
    public String admin()  {
        return "user/admin";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/";
    }
}


