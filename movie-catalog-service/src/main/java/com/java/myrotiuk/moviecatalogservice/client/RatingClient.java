package com.java.myrotiuk.moviecatalogservice.client;

import com.java.myrotiuk.moviecatalogservice.dto.RatingUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ivan on 10.03.2021. All rights reserved.
 * <p>
 * In the @FeignClient annotation the String value ("stores" above) is an arbitrary client name,
 * which is used to create either a Ribbon load-balancer
 * (see below for details of Ribbon support) or Spring Cloud LoadBalancer.
 * <p>
 * The load-balancer client above will want to discover the physical addresses for the "stores" service.
 * If your application is a Eureka client then it will resolve the service in the Eureka service registry.
 * If you don’t want to use Eureka, you can simply configure a list of servers in your external configuration
 */
@FeignClient(name = "rating-data-service"/*, url = "http://rating-data-service/api/v1/rating"*/)
//http://rating-data-service
@RequestMapping("/api/v1/rating")
public interface RatingClient {
    @GetMapping(value = "/users/{userId}")
    public RatingUserDto getRatingUserDto(@PathVariable("userId") Long userId);
}
