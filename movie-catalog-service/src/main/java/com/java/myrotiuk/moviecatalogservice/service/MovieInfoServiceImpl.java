package com.java.myrotiuk.moviecatalogservice.service;

import com.java.myrotiuk.moviecatalogservice.dto.MovieInfoDto;
import com.java.myrotiuk.moviecatalogservice.dto.RatingDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Ivan on 12.03.2021. All rights reserved.
 */
@Service
@RequiredArgsConstructor
public class MovieInfoServiceImpl implements MovieInfoService {

    private final WebClient.Builder webClientBuilder;

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo",
            //Configuring Hystrix
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            }
    )
    public MovieInfoDto getMovieInfoDto(RatingDto r) {
        return webClientBuilder.build()
                .get()
                .uri("http://movie-info-service/api/v1/movie-info/" + r.getMovieId())
                .retrieve()
                .bodyToMono(MovieInfoDto.class)
                .block();
    }

    private MovieInfoDto getFallbackMovieInfo(RatingDto r) {
        return MovieInfoDto.builder()
                .movieId(r.getMovieId())
                .name("Movie name not found")
                .desc("")
                .build();
    }
}
