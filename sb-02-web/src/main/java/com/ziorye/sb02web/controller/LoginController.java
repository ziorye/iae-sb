package com.ziorye.sb02web.controller;

import com.ziorye.sb02web.bean.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("login")
    String show() {
        return "login";
    }

    @PostMapping("login")
    String login(User user, HttpSession session) {
        if (user != null && "secret".equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            return "redirect:/home";
        }

        return "redirect:/login";
    }

    @GetMapping("/home")
    String home() {
        return "home";
    }
}