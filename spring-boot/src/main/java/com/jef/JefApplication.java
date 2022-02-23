package com.jef;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableApolloConfig
@MapperScan("com.jef.dao") //扫描的mapper
@SpringBootApplication
public class JefApplication {

    public static void main(String[] args) {
        SpringApplication.run(JefApplication.class, args);
    }

}
