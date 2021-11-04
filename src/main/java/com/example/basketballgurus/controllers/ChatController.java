package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.repositories.RosterRepository;
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {
    private final UserRepository userDao;
    private final RosterRepository rosterDao;
    private final LeaguesRepository leagueDao;

    public ChatController(UserRepository userDao, RosterRepository rosterDao, LeaguesRepository leagueDao) {
        this.userDao = userDao;
        this.rosterDao = rosterDao;
        this.leagueDao = leagueDao;
    }

    @GetMapping("/chat/create")
    public String leagueChannel(Model model) {
        User userInSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userprofile", userInSession);
        return "/chatPub";
    }

//    @GetMapping("/chat/create")
//    public String memberChat(Model model) {
//        User userInSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<Roster> allRosters = rosterDao.findById();
//        return "/chatPub";
//    }

    @PostMapping("/chat/create")
    public String memberChatSend(@ModelAttribute User user,Model model){
        User currentUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.save(currentUser);
        return "redirect:/chatPub";
    }


    @GetMapping("/chat/{id}")
    public String redirectUser(@PathVariable int id, Model model, Principal principal){
        User user = userDao.findByUsername(principal.getName());
        List<Roster> rosters = rosterDao.getByLeagueId(leagueDao.getById(id));
        System.out.println(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("rosters", rosters);
        return "chatPub";
    }

}