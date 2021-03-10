package com.java.myrotiuk.ratingdataservice.controller;

import com.java.myrotiuk.ratingdataservice.dto.RatingDto;
import com.java.myrotiuk.ratingdataservice.dto.RatingUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by Ivan on 21.11.2020. All rights reserved.
 */
@RestController
@RequestMapping("/api/v1/rating")
public class RaringController {

    @GetMapping("/{movieId}")
    public RatingDto getRating(@PathVariable("movieId") Long movieId) {
        return RatingDto.builder()
                .movieId(movieId)
                .rating(4.4)
                .build();
    }

    @GetMapping("/users/{userId}")
    public RatingUserDto getUserRating(@PathVariable("userId") Long userId) {
        return RatingUserDto.builder()
                .ratingDtoList(Arrays.asList(
                        new RatingDto(1234L, 4d),
                        new RatingDto(5678L, 3d))
                ).build();
    }

}
