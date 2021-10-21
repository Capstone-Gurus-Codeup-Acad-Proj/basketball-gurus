package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    private final UserRepository userDao;

    public ProfileController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public String ProfilePage() {
        return "profile";
    }

    @GetMapping("/{username}/publicprofile")
    public String showUserProfile(@PathVariable String username, Model model){
        User otherProfile = userDao.findByUserName(username);
        model.addAttribute("viewOtherProfile", otherProfile);
        return "publicProfile";
    }
//    @GetMapping("/profile")
//    public String ownProfile(Model model){
//        User userLoggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userProfile = userDao.getById((long) userLoggedIn.getId());
//        model.addAttribute("viewOwnProfile", userProfile);
//        return "personalProfile";
//    }

}
