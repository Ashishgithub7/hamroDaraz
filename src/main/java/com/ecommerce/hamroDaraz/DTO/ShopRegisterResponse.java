package com.ecommerce.hamroDaraz.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime createdAt;
}
