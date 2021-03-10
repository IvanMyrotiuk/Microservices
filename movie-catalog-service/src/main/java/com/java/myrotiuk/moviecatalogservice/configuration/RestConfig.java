package com.java.myrotiuk.moviecatalogservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Ivan on 22.11.2020. All rights reserved.
 */
@Configuration
public class RestConfig {

    @Bean
    /**
     *  Do a discovery in a load balanced way and where we do not call directly particular service.
     *  We just give the hint to eureka server (name of the service which we want to call) to
     *  server discovery which return to eureka client real host and port number for service which will be
     *  called by client eureka service
     */
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }
}
