package com.ziorye.controller;

import com.ziorye.config.YAML;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @RequestMapping("/")
    String home() {
        log.info("=== HelloController@home ===");
        return "Hello World!";
    }

    @Autowired YAML yaml;
    @GetMapping("/yaml")
    YAML yaml() {
        return yaml;
    }
}
