package com.challenge.santander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableFeignClients(basePackages = {"com.challenge.santander.feign.client"})
@EnableRetry
@SpringBootApplication
public class OrchestratorMeetupApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestratorMeetupApiApplication.class, args);
    }

}
