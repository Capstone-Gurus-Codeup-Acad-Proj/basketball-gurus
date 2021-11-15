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

import javax.sql.RowSet;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;
    private final RosterRepository rosterDao;
    private RowSet userEdited;

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
        user.setProfilePicture("https://pbs.twimg.com/media/Efpe1GYX0AYHuoL.jpg");
        user.setBannerUrl("https://cdn.kapwing.com/final_5dcc99aba3f32c0013ff8b46_45583.jpg");
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
        return "redirect:/login";
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

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public String submitEdit(@RequestParam(value = "profilePicture") String profilePicture, @RequestParam(value = "bannerUrl") String bannerUrl, Principal principal) {

        User user = userDao.findByUsername(principal.getName());
        if (!profilePicture.equals("")){
            user.setProfilePicture(profilePicture);
        }
        if (!bannerUrl.equals("")){
            user.setBannerUrl(bannerUrl);
        }
//        user.setBio(Bio);
//
//
//        userEdited.setUsername(username);
//        userEdited.setProfilePicture(profilePicture);
//        userEdited.setBio(Bio);
//        userEdited.setBannerUrl(BannerUrl);
//        return"redirect:/login";

        userDao.save(user);
        return"redirect:/profile";

    }
    @PostMapping("logout")
    public String logout(){

        return "redirect:/login";

    }
}