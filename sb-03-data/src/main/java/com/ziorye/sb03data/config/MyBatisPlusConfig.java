package com.ziorye.sb03data.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.ziorye.sb03data.mapper")
@Configuration
public class MyBatisPlusConfig {
}
