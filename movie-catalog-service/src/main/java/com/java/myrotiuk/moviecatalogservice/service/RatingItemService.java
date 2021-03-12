package com.java.myrotiuk.moviecatalogservice.service;

import com.java.myrotiuk.moviecatalogservice.dto.RatingUserDto;

/**
 * Created by Ivan on 12.03.2021. All rights reserved.
 */
public interface RatingItemService {

    RatingUserDto getRatingUserDto(Long userId);

}
