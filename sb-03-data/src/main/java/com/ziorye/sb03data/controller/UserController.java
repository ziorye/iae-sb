package com.ziorye.sb03data.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziorye.sb03data.bean.User;
import com.ziorye.sb03data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users")
    public String index(@RequestParam(value = "p", defaultValue = "1") Integer p, Model model) {
        Page<User> page = userService.page(new Page<>(p, 3));
        model.addAttribute("page", page);
        return "users";
    }
}
