package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.RosterRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.basketballgurus.repositories.UserRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;
    private final RosterRepository rosterDao;

    public UserController(UserRepository userDao, GameBarService gm, PasswordEncoder passwordEncoder, RosterRepository rosterDao) {
        this.userDao = userDao;
        this.gm = gm;
        this.passwordEncoder = passwordEncoder;
        this.rosterDao = rosterDao;
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
        return "user/profile";
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
        List<Roster> rosters = rosterDao.getByUserId(user);
        model.addAttribute("user", user);
        model.addAttribute("rosters", rosters);
        return "user/profile";
    }
    @GetMapping("/profile/edit/{id}")
    public String EditProfile(Model model, @PathVariable long id){
        User user = userDao.getById(id);

        model.addAttribute("user", user.getUsername());
        model.addAttribute("user", user.getProfilePicture());
        model.addAttribute("user", user.getBannerUrl());
        model.addAttribute("user", user.getBio());
        return "user/profile";
    }

    @PostMapping("/profile/edit/{id}")
    public String submitEdit(
            @PathVariable long id, @RequestParam(name = "username") String username,
            @RequestParam (name = "profilePicture") String profilePicture,
            @RequestParam (name = "Bio") String Bio,
            @RequestParam (name = "BannerUrl") String BannerUrl){

        User userEdited = userDao.getById(id);

        userEdited.setUsername(username);
        userEdited.setProfilePicture(profilePicture);
        userEdited.setBio(Bio);
        userEdited.setBannerUrl(BannerUrl);
        return"redirect:/profile";
    }
    @PostMapping("logout")
    public String logout(){

        return "redirect:/login";

    }
}