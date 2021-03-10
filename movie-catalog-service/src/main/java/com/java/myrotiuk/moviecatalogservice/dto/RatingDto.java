package com.java.myrotiuk.moviecatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Ivan on 21.11.2020. All rights reserved.
 */
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class RatingDto {
    private Long movieId;
    private Double rating;
}
