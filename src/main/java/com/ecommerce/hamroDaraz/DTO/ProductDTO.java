package com.ecommerce.hamroDaraz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long shopId;

    private String productName;

    private Long stock;

    private Long price;

    private String description;

    private String imageUrl;
}
