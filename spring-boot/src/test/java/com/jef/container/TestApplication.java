package com.jef.container;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.MySQLContainer;

/**
 * @author liangpengyu
 * @since 2023/8/4 2:46
 */
@Profile("integrated")
@SpringBootApplication
@ComponentScan("com.jef")
@MapperScan("com.jef.dao")
@Import(DependentMiddlewareInitializer.class)
public class TestApplication {

    private static final MySQLContainer<?> MYSQL = ContainerHelper.createMySqlContainer();

    static {
        System.setProperty("spring.datasource.url", MYSQL.getJdbcUrl());
        System.setProperty("spring.datasource.username", MYSQL.getUsername());
        System.setProperty("spring.datasource.password", MYSQL.getPassword());
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
