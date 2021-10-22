package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.basketballgurus.repositories.UserRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private final UserRepository userDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userDao, GameBarService gm, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.gm = gm;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String showCreateForm(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute User user, Model model) {
        System.out.println(user);
        model.addAttribute("games", gm.getTodaysGames());
        String hash = passwordEncoder.encode(user.getPassword());
        System.out.println("Passwords match is :");
        System.out.println(passwordEncoder.matches(hash, user.getPassword()));
        user.setPassword(hash);
//        user.setActive(true);
        userDao.save(user);
        return "redirect:/login";
    }
}