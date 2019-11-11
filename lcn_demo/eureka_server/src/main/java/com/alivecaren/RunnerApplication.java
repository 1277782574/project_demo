package com.alivecaren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RunnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunnerApplication.class,args);
    }


}
