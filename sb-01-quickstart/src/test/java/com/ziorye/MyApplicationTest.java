package com.ziorye;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyApplicationTest {
    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Test
    @DisplayName("测试IoC容器中存在名称为 myApplication 的 bean" +
            " 因为主程序类名叫：MyApplication 且它本身也是 @Configuration")
    void testContainsMyApplicationBean() {
        assertTrue(applicationContext.containsBean("myApplication"));
    }

    @Test
    @DisplayName("测试IoC容器中存在名称为 dispatcherServlet 的 bean" +
            " 因为引入了 spring-boot-starter-web")
    void testContainsDispatcherServletBean() {
        assertTrue(applicationContext.containsBean("dispatcherServlet"));
    }
}