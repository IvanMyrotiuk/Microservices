package com.java.myrotiuk.moviecatalogservice.service;

import com.java.myrotiuk.moviecatalogservice.client.RatingClient;
import com.java.myrotiuk.moviecatalogservice.dto.RatingDto;
import com.java.myrotiuk.moviecatalogservice.dto.RatingUserDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Ivan on 12.03.2021. All rights reserved.
 */
@Service
@RequiredArgsConstructor
public class RatingItemServiceImpl implements RatingItemService {

    private final RatingClient client;

    @Override
    //In order to configure separate thread pull for this method and apply The bulkhead pattern
    @HystrixCommand(fallbackMethod = "getFallbackRating",
            threadPoolKey = "ratingPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public RatingUserDto getRatingUserDto(Long userId) {

        return client.getRatingUserDto(userId);
    }

    private RatingUserDto getFallbackRating(Long userId) {
        return RatingUserDto.builder()
                .ratingDtoList(Collections.singletonList(RatingDto.builder()
                        .movieId(0L)
                        .rating(0.0)
                        .build()))
                .build();
    }
}
