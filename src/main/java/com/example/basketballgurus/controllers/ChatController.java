package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.repositories.RosterRepository;
import com.example.basketballgurus.repositories.UserRepository;
import com.example.basketballgurus.services.GameBarService;
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
    private final RosterRepository rosterDao;
    private final LeaguesRepository leagueDao;
    private final GameBarService gm;

    public ChatController(UserRepository userDao, RosterRepository rosterDao, LeaguesRepository leagueDao, GameBarService gm) {
        this.userDao = userDao;
        this.rosterDao = rosterDao;
        this.leagueDao = leagueDao;
        this.gm = gm;
    }

    @GetMapping("/chat/create")
    public String leagueChannel(Model model) {
        User userInSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userprofile", userInSession);
        return "/chatPub";
    }



    @PostMapping("/chat/create")
    public String memberChatSend(@ModelAttribute User user,Model model){
        User currentUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.save(currentUser);
        return "redirect:/";
    }


    @GetMapping("/chat")
    public String redirectUser(Model model, Principal principal){
        User user = userDao.findByUsername(principal.getName());
//        List<Roster> rosters = rosterDao.getByLeagueId(leagueDao.getById(id));
        List<Roster> rosters = rosterDao.getByUserId(user);
        System.out.println(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("rosters", rosters);
        return "chatPub";
    }

}