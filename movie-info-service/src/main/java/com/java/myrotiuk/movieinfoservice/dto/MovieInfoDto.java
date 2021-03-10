package com.java.myrotiuk.movieinfoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by Ivan on 21.11.2020. All rights reserved.
 */
@AllArgsConstructor
@Builder
@Getter
public class MovieInfoDto {

    private Long movieId;
    private String name;
    private String desc;

}
