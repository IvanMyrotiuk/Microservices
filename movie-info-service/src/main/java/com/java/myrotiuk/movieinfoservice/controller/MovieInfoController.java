package com.java.myrotiuk.movieinfoservice.controller;

import com.java.myrotiuk.movieinfoservice.client.MovieClient;
import com.java.myrotiuk.movieinfoservice.dto.MovieInfoDto;
import com.java.myrotiuk.movieinfoservice.dto.MovieSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ivan on 21.11.2020. All rights reserved.
 * <p>
 * In order to run several instances we may do it with
 * java -Dserver.port=8098 -jar movie-info-service____.jar
 * and second server will be registered with the same eureka server
 * <p>
 * Load balancing happening on client side
 */
@RestController
@RequestMapping("/api/v1/movie-info")
//@RequiredArgsConstructor
public class MovieInfoController {

//    @Value("${api.key}")
//    private String apiKey;
//    private final MovieClient movieClient;

    @GetMapping("/{movieId}")
    public MovieInfoDto getMovieInfo(@PathVariable("movieId") Long movieId) {

        //call to the third party end point. It does not work but we may add here another one api
        //MovieSummaryDto movieSummary = movieClient.getMovieSummary(movieId, apiKey);

        return MovieInfoDto.builder()
                .movieId(movieId)
                .name("Transformers")
                .desc("Test"+movieId)
                .build();
    }

}
