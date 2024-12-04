package com.ecommerce.hamroDaraz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long stock;

    private Long price;

    private String imageUrl;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:SS")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:SS")
    private LocalDateTime updatedAt;
}
