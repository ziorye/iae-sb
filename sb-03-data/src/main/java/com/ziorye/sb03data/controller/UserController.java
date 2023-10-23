package com.ziorye.sb03data.controller;

import com.ziorye.sb03data.bean.User;
import com.ziorye.sb03data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users")
    public String index(Model model) {
        List<User> users = userService.list();
        model.addAttribute("users", users);
        return "users";
    }
}
