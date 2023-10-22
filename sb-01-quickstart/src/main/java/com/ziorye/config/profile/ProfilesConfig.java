package com.ziorye.config.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfilesConfig {
    @Bean
    @Profile("dev")
    BeanDev beanDev() {
        return new BeanDev();
    }

    @Bean
    @Profile("prod")
    BeanProd beanProd() {
        return new BeanProd();
    }
}
