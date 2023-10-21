package com.ziorye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MyApplication.class, args);

        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));

        boolean containsMyApplicationBean = applicationContext.containsBean("myApplication");
        System.out.println(containsMyApplicationBean);

        boolean containsDispatcherServletBean = applicationContext.containsBean("dispatcherServlet");
        System.out.println(containsDispatcherServletBean);
    }

}


