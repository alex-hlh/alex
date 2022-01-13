package com.bus.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: Application
 * @Description:
 * @Author: hlh
 * @Date: 2021-12-23 11:08
 */
@SpringBootApplication
@MapperScan("com.bus.server.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
