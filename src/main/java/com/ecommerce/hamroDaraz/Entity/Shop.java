package com.ecommerce.hamroDaraz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Shop Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9&.\\-' ]{3,50}$", message = "\"Invalid shop name. The name must be 3 to 50 characters long and can only contain letters, numbers, spaces, and the following special characters: &, ., -, '.\"\n")
    private String name;

    @NotNull(message = "Shop's contact information is required")
    @NotBlank(message = "Enter some contact information")
    @Size(min = 5, max = 200, message="Contact Info's length should be between 5 and 200")
    private String contactInfo;

    private String logoUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime updatedAt;

    @NotNull(message = "Shop's description is required")
    @NotBlank(message = "Enter shop's information")
    @Size(min = 5, max = 200, message="Shop description length should be between 5 and 200")
    private String description;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user-id")
    private User user;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;



}
