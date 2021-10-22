package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.basketballgurus.repositories.UserRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final GameBarService gm;

    public ProfileController(UserRepository userDao, PasswordEncoder passwordEncoder, GameBarService gm) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.gm = gm;
    }

//    @GetMapping("/profile")
//    public String ownProfile(Model model){
//        User userLoggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userProfile = userDao.getById((long) userLoggedIn.getId());
//        model.addAttribute("viewOwnProfile", userProfile);
//        return "personalProfile";
//    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
 //       User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getById(1L);
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("user",user);
//        if (!user.isActive()) {
//            return "redirect:/home";
//        }
        return "/profile";
    }
}
