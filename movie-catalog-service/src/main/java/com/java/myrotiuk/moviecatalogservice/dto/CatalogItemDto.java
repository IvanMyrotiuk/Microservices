package com.java.myrotiuk.moviecatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by Ivan on 21.11.2020. All rights reserved.
 */
@Builder
@Getter
@AllArgsConstructor
public class CatalogItemDto {
    private String name;
    private String description;
    private Double rating;
}
