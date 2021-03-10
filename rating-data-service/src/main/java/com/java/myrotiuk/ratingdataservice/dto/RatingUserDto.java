package com.java.myrotiuk.ratingdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by Ivan on 22.11.2020. All rights reserved.
 */
@AllArgsConstructor
@Getter
@Builder
public class RatingUserDto {

    private List<RatingDto> ratingDtoList;

}
