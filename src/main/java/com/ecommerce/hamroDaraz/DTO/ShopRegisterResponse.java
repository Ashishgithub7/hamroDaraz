package com.ecommerce.hamroDaraz.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopRegisterResponse {

    private String name;

    private String contactInfo;

    private String logoUrl;

    private String description;

    private LocalDateTime createdAt;
}
