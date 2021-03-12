package com.java.myrotiuk.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Client service discovery will register themselves at server discovery
 * Client doesn't send request directly to the service. It sends request to server discovery, service discovery gives
 * an address of the service which client needs (by spring.application.name) and then client makes call to that service
 * with address which was provided by server discovery.
 * <p>
 * Eureka client always sends a ping (constant hart beat) to eureka server and tell that it steal alive and if ping isn't there
 * eureka server remove it from register.
 * <p>
 * Situation when discovery server is down. Client ask eureka server to provide address, no response,
 * okay then client will look to the cache for now.
 * <p>
 * And it is done by eureka client because of loadBalance annotation
 * <p>
 * Spring cloud use Eureka technology. Client Server discovery is a pattern where discovering happens on client side
 * <p>
 * Also we have got server discovery pattern when we call server and then server call specific service in order to pass information
 * <p>
 * But Spring Cloud uses client side service discovery. The libraries which facilitate service discovery
 * will resigned on the client.
 * Where to call(asking eureka server) and making actual call(we know from eureka server) happen on client side
 **/
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
@EnableEurekaClient
public class MovieCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
