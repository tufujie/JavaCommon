package com.jef.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrderMain8000 {
    public static void main(String[] args) {
        // http://localhost:8001/payment/get/1
        SpringApplication.run(OrderMain8000.class, args);
    }
}