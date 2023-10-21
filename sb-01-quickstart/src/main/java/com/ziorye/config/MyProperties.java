package com.ziorye.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my")
@Data
public class MyProperties {
    private String name;
    private int age;
}
