package com.ziorye.sb02web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafQuickstartController {
    @GetMapping("quickstart")
    String quickstart() {
        return "quickstart";
    }
}
