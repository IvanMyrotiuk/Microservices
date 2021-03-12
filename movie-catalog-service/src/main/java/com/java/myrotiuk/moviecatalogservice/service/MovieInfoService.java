package com.java.myrotiuk.moviecatalogservice.service;

import com.java.myrotiuk.moviecatalogservice.dto.MovieInfoDto;
import com.java.myrotiuk.moviecatalogservice.dto.RatingDto;

/**
 * Created by Ivan on 12.03.2021. All rights reserved.
 */
public interface MovieInfoService {

    MovieInfoDto getMovieInfoDto(RatingDto r);

}
