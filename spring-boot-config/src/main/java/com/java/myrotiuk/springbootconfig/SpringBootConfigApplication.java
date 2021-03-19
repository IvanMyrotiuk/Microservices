package com.java.myrotiuk.springbootconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * We may have got several beans that is implement the same interface but have annotation @Profile("dev/test/aqa")
 * with specific env. So that specific bean will be instantiated depends on the chosen profile.
 * <p>
 * General things about properties files and profiling along with connecting to config server
 * Client
 */

@SpringBootApplication
public class SpringBootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigApplication.class, args);
    }

}
