<<<<<<< HEAD
//package com.example.basketballgurus.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class AuthenticationController {
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "user/login";
//    }
//}
=======
package com.example.basketballgurus.controllers;

import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    private final GameBarService gm;

    public AuthenticationController(GameBarService gm) {
        this.gm = gm;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        return "user/login";
    }
}
>>>>>>> 12cff3f637feb317163226bab761b2dc7789e0e2
