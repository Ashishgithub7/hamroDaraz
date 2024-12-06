package com.ecommerce.hamroDaraz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, max = 50, message = "Product name must be between 2 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9&.\\-' ]{3,50}$", message = "\"Invalid shop name. The name must be 3 to 50 characters long and can only contain letters, numbers, spaces, and the following special characters: &, ., -, '.\"\n")
    private String name;

    @NotNull(message = "Product's description is required")
    @NotBlank(message = "Enter product's information")
    @Size(min = 5, max = 200, message="Product description length should be between 5 and 200")
    private String description;

    @NotNull(message = "Product's stock should be listed")
    @PositiveOrZero(message = "Stock size should be positive or zero")
//    @Pattern(regexp="^[0-9]+$",message = "Invalid stock size. Stock should be in number")
    private Long stock;

    @NotNull(message = "Product's price should be listed")
    @PositiveOrZero(message = "Price should be positive or zero")
//    @Pattern(regexp="^[0-9]+$",message = "Price should be in number")
    private Long price;

    private String imageUrl;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:SS")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:SS")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "shop-id")
    private Shop shop;
}
