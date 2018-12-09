package com.zakharenkov.shop.service.dto;

import com.zakharenkov.shop.database.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String brand;
    private String model;
    private String description;
    private String image;
    private String category;
    private Integer price;
}
