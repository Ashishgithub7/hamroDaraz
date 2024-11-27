package com.ecommerce.hamroDaraz.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopRegisterRequest {

    @Id
    private Long id;

    private String name;

    private String contactInfo;

    private String logoUrl;

    private String description;

    private String token;
}
