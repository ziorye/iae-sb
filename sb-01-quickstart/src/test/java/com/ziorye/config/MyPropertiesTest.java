package com.ziorye.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyPropertiesTest {
    @Test
    void testConfigurationProperties(@Autowired MyProperties myProperties) {
        assertEquals("John", myProperties.getName());
        assertEquals(18, myProperties.getAge());
    }
}