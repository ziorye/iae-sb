package com.ziorye.controller;

import com.ziorye.config.YAML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @Autowired YAML yaml;
    @GetMapping("/yaml")
    YAML yaml() {
        return yaml;
    }
}
