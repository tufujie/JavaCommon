package com.jef.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// Eureka服务端
@EnableEurekaServer
@SpringBootApplication
public class EurekaMain8761 {
    public static void main(String[] args) {
        // http://localhost:8761/
        SpringApplication.run(EurekaMain8761.class, args);
    }
}