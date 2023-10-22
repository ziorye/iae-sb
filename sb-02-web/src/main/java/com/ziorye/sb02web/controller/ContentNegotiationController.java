package com.ziorye.sb02web.controller;

import com.ziorye.sb02web.bean.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentNegotiationController {
    @GetMapping("person")
    public Person getPerson() {
        Person person = new Person();
        person.setUserName("John");
        person.setAge(18);
        return person;
    }
}
