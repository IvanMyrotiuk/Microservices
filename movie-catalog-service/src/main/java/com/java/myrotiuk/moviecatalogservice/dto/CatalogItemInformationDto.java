package com.java.myrotiuk.moviecatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by Ivan on 22.11.2020. All rights reserved.
 */
@AllArgsConstructor
@Getter
@Builder
public class CatalogItemInformationDto {
    private List<CatalogItemDto> catalogItemDtoList;
}
