package com.ziorye.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(properties = "spring.profiles.active=prod")
public class ProfilesTest {
    @Test
    void testProfiles(@Autowired Environment env) {
        Assertions.assertEquals("myApp - prod", env.getProperty("spring.application.name"));
    }
}
