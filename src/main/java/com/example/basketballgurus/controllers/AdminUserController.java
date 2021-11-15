package com.example.basketballgurus.controllers;


import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class AdminUserController {
    private final UserRepository userDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;
 

    public AdminUserController(UserRepository userDao, GameBarService gm, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.gm = gm;
        this.passwordEncoder = passwordEncoder;

    }

        @GetMapping("/login")
        public String LoginForm() {
            return "user/login";
        }

//    @GetMapping("/signup")
//    public String showCreateForm(Model model) {
//        model.addAttribute("games", gm.getTodaysGames());
//        model.addAttribute("user", new User());
//        return "user/register";
//    }
//
//    @PostMapping("/signup")
//    public String create(@ModelAttribute User user, Model model) {
//        model.addAttribute("games", gm.getTodaysGames());
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/admin")
//    public String admin(Model model) {
//        List<User> usersToShow = userDao.findAll();
//        model.addAttribute("users", usersToShow);
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userInDB = userDao.getById(currentUser.getId());
//        Object admin = null;
//        model.addAttribute("theCurrenUser", Objects.equals(userInDB.getRole(), admin));
//        return checkIfAdmin("admin");
//    }
//
//    public String checkIfAdmin(String originalTemplate) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userInDB = userDao.getById(currentUser.getId());
//        String admin = null;
//        if (userInDB.getRole() == admin) {
//            return originalTemplate;
//
//        } else {
//            return "redirect:/";
//        }
//    }
//    @PostMapping("/admin/profile/{id}")
//    public String changeStatus (@PathVariable long id, @RequestParam(name="status") String status){
//        User userInDB = userDao.getById(id);
////        userInDB.isActive("true");
//        userDao.save(userInDB);
//        return  "redirect:/admin";
//    }
//
//    @PostMapping(value = "/deleteUser/{postId}")
//    public String deleteUser(Model model, @PathVariable String postId) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userInDB = userDao.getById(currentUser.getId());
//        Object admin = null;
//        model.addAttribute("theCurrentUser", Objects.equals(userInDB.getRole(), null));
//
//        return "redirect:/";
//    }
}