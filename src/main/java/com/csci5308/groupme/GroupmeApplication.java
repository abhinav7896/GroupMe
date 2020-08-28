package com.csci5308.groupme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.csci5308")
@SpringBootApplication
public class GroupmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupmeApplication.class, args);
    }

}
