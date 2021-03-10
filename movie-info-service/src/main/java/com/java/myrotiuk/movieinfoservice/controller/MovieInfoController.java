package com.java.myrotiuk.movieinfoservice.controller;

import com.java.myrotiuk.movieinfoservice.dto.MovieInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ivan on 21.11.2020. All rights reserved.
 *
 * In order to run several instances we may do it with
 * java -Dserver.port=8098 -jar movie-info-service____.jar
 * and second server will be registered with the same eureka server
 *
 * Load balancing happening on client side
 */
@RestController
@RequestMapping("/api/v1/movie-info")
public class MovieInfoController {

    @GetMapping("/{movieId}")
    public MovieInfoDto getMovieInfo(@PathVariable("movieId") Long movieId){
        return MovieInfoDto.builder()
                .movieId(movieId)
                .name("Transformers")
                .desc("Test"+movieId)
                .build();
    }

}
