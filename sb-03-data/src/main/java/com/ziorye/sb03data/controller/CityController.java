package com.ziorye.sb03data.controller;

import com.ziorye.sb03data.bean.City;
import com.ziorye.sb03data.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/city/{id}")
    public City findById(@PathVariable Long id) {
        return cityService.findById(id);
    }
}
