package com.zakharenkov.shop.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterDto {

    private Integer tv;
    private Integer audio;
    private Integer minPrice;
    private Integer maxPrice;
    private String brand;
    private String orderBy;
    private Integer pageSize;
    private Integer page;

}
