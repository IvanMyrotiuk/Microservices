package com.java.myrotiuk.moviecatalogservice.controller;

import com.java.myrotiuk.moviecatalogservice.client.RatingClient;
import com.java.myrotiuk.moviecatalogservice.dto.CatalogItemDto;
import com.java.myrotiuk.moviecatalogservice.dto.CatalogItemInformationDto;
import com.java.myrotiuk.moviecatalogservice.dto.MovieInfoDto;
import com.java.myrotiuk.moviecatalogservice.dto.RatingUserDto;
import com.netflix.discovery.DiscoveryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 21.11.2020. All rights reserved.
 * During communicating between microservices for security we may use https and
 * basic authorization where we put credentials into header
 */
@RestController
@RequestMapping("/api/v1/movie-catalog")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final RatingClient client;
    //with discovery client we may retrieve server host, port and so on by name of service
    //get instances ...
    //private final DiscoveryClient discoveryClient;

    @GetMapping("/{userId}")
    public CatalogItemInformationDto getCatalog(@PathVariable("userId") Long userId) {

        //RatingUserDto ratingUserDto = restTemplate.getForObject("http://rating-data-service/api/v1/rating/users/" + userId, RatingUserDto.class);
        RatingUserDto ratingUserDto = client.getRatingUserDto(userId);

        List<CatalogItemDto> catalogItemDtoList = ratingUserDto.getRatingDtoList().stream().map(r -> {
            //1.with rest template
            //MovieInfoDto movieInfoDto = restTemplate.getForObject("http://localhost:8082/api/v1/movie-info/" + r.getMovieId(), MovieInfoDto.class);

            //2.with reactive WebClient
            MovieInfoDto movieInfoDto = webClientBuilder.build()
                    .get()
                    .uri("http://movie-info-service/api/v1/movie-info/" + r.getMovieId())
                    .retrieve()
                    .bodyToMono(MovieInfoDto.class)
                    .block();

            return CatalogItemDto.builder()
                    .name(movieInfoDto.getName())
                    .description(movieInfoDto.getDesc())
                    .rating(r.getRating())
                    .build();
        }).collect(Collectors.toList());

        return CatalogItemInformationDto.builder()
                .catalogItemDtoList(catalogItemDtoList)
                .build();
    }

}
//better to do it with butch operation send List of user id to rating-data-service, then collect all movie ids
//and send to movie-info-service and gett all movies info map movie id -> rating; movie id -> movie info; and at stream travers two maps creating response