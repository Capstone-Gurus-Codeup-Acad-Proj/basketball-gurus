package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.models.Team;
import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {
    private final UserRepository userDao;

    public ChatController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/chat/create")
    public String memberChat(Model model) {
        User userInSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userprofile", userInSession);
        return "/chatPub";
    }

    @PostMapping("/chat/create")
    public String memberChatSend(@ModelAttribute User user,Model model){
        User currentUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.save(currentUser);
        return "redirect:/chatPub";
    }

    @GetMapping("/chat")
    public String redirectUser(Model model, Principal principal){
        User user = userDao.findByUsername(principal.getName());
        System.out.println(user.getUsername());
        model.addAttribute("user", user);
        return "chatPub";
    }

}