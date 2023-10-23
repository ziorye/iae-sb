package com.ziorye.sb02web.controller;

import com.ziorye.sb02web.bean.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @GetMapping("login")
    String show() {
        return "login";
    }

    @PostMapping("login")
    String login(User user, HttpSession session, RedirectAttributes redirectAttributes) {
        if (user != null && "secret".equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            redirectAttributes.addFlashAttribute("msg", "got it!");
            return "redirect:/home";
        }

        redirectAttributes.addFlashAttribute("msg", "邮箱或密码错误");
        return "redirect:/login";
    }

    @GetMapping("/home")
    String home() {
        return "home";
    }
}