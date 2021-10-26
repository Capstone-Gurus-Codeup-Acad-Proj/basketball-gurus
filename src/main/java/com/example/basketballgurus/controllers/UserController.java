package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.basketballgurus.repositories.UserRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, GameBarService gm, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.gm = gm;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/sign-up")
    public String showCreateForm(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/sign-up")
    public String create(@ModelAttribute User user, Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setActive(user.isActive());
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/create")
    public String memberProfile(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        User userInSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userprofile", userInSession);
        return "/profile";
    }
    @PostMapping("/profile/create")
    public String memberProfileSend(@ModelAttribute User user,Model model){
        model.addAttribute("games", gm.getTodaysGames());
        User currentUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.save(currentUser);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String redirectProfile(Model model, Principal principal){
        model.addAttribute("games", gm.getTodaysGames());
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(principal.getName());
        System.out.println(user.getUsername());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("logout")
    public String logout(){
        return "redirect/home";
    }
}