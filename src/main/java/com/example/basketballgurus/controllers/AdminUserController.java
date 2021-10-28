package com.example.basketballgurus.controllers;


import com.example.basketballgurus.models.AdminUser;
import com.example.basketballgurus.repositories.AdminUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminUserController {
    private final AdminUserRepository adminUserDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(AdminUserRepository adminUserDao, GameBarService gm, PasswordEncoder passwordEncoder) {
        this.adminUserDao = adminUserDao;
        this.gm = gm;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/sign-up")
    public String showCreateForm(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("adminUser", new AdminUser());
        return "user/register";
    }

    @PostMapping("/sign-up")
    public String create(@ModelAttribute AdminUser adminuser, Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        String hash = passwordEncoder.encode(adminuser.getAdminPassword());
        adminuser.setAdminPassword(hash);
//        adminUserDao.save(adminuser);
        return "redirect:/login";
    }
}