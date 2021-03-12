package com.java.myrotiuk.moviecatalogservice.controller;

import com.java.myrotiuk.moviecatalogservice.dto.CatalogItemDto;
import com.java.myrotiuk.moviecatalogservice.dto.CatalogItemInformationDto;
import com.java.myrotiuk.moviecatalogservice.dto.MovieInfoDto;
import com.java.myrotiuk.moviecatalogservice.dto.RatingUserDto;
import com.java.myrotiuk.moviecatalogservice.service.MovieInfoService;
import com.java.myrotiuk.moviecatalogservice.service.RatingItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

//    private final RestTemplate restTemplate;
//    private final WebClient.Builder webClientBuilder;
//    private final RatingClient client;
    //with discovery client we may retrieve server host, port and so on by name of service
    //get instances ...
    //private final DiscoveryClient discoveryClient;

    //Fallback mechanism will be called for specific service where circuit breaker is applied.
    //And another one service will provide us data
    private final MovieInfoService movieInfoService;
    private final RatingItemService ratingItemService;

    /**
     * We tell that this method need circuit breakers.
     * Because this method is making external calls and those calls may lead to timeout and
     * threads being queued up and that could result in the whole app going down
     */
    //@HystrixCommand(fallbackMethod = "getFallbackCatalog") made it granular
    @GetMapping("/{userId}")
    public CatalogItemInformationDto getCatalog(@PathVariable("userId") Long userId) {

        //RatingUserDto ratingUserDto = restTemplate.getForObject("http://rating-data-service/api/v1/rating/users/" + userId, RatingUserDto.class);
        RatingUserDto ratingUserDto = ratingItemService.getRatingUserDto(userId);

        List<CatalogItemDto> catalogItemDtoList = ratingUserDto.getRatingDtoList().stream().map(r -> {
            //1.with rest template
            //MovieInfoDto movieInfoDto = restTemplate.getForObject("http://localhost:8082/api/v1/movie-info/" + r.getMovieId(), MovieInfoDto.class);

            //2.with reactive WebClient
            MovieInfoDto movieInfoDto = movieInfoService.getMovieInfoDto(r);

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

    //Call this method when circuit breaker is applied
    public CatalogItemInformationDto getFallbackCatalog(@PathVariable("userId") Long userId) {
        return CatalogItemInformationDto.builder()
                .catalogItemDtoList(Arrays.asList(new CatalogItemDto("No movie", "", 0.0)))
                .build();
    }

}
//better to do it with butch operation send List of user id to rating-data-service, then collect all movie ids
//and send to movie-info-service and gett all movies info map movie id -> rating; movie id -> movie info; and at stream travers two maps creating response