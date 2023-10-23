package com.ziorye.sb02web.controller;

import com.ziorye.sb02web.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    @GetMapping("users")
    public String index(Model model) {
        List<User> users = Arrays.asList(
                new User("john", "john@test.com", "secret"),
                new User("tom", "tom@test.com", "secret"),
                new User("test", "test@test.com", "secret")
        );
        model.addAttribute("users", users);
        return "users";
    }
}
