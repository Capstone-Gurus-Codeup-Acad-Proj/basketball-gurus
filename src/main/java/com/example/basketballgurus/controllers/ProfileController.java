package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

@Controller
public class ProfileController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public ProfileController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/profile")
//    public String ProfilePage() {
//        return "profile";
//    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUserName(currentUser.getUserName());
        if (!user.isActive()) {
            return "redirect:/home";
        }
        return "redirect:/profile/" + currentUser.getId();
    }

    @GetMapping("/profile/{id}")
    public String viewById(@PathVariable long id, Model model) {
        User currentUser = userDao.getById(id);
        model.addAttribute("user", currentUser);
        return "profile/Idview";
    }

    @GetMapping("/profile/edit")
    public String EditProfile(Model model) {
        User loggedin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUserName(loggedin.getUserName());
        model.addAttribute("user", (user).getUserName());
        return "profile/edit";
    }

    @RequestMapping(value = "profile/{id}", method = RequestMethod.POST)
    public String getUserName(@RequestParam(name = "username") String username,Model model) {
        User currentUser = userDao.findByUserName(username);
        model.addAttribute("users", currentUser);
        return "/profile";
    }
}
